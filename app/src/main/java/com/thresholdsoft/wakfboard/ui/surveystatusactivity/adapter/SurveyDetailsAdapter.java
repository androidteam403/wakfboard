package com.thresholdsoft.wakfboard.ui.surveystatusactivity.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.loopeer.itemtouchhelperextension.Extension;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;
import com.thresholdsoft.wakfboard.R;
import com.thresholdsoft.wakfboard.data.db.model.SurveyEntity;
import com.thresholdsoft.wakfboard.databinding.ListItemMainBinding;
import com.thresholdsoft.wakfboard.ui.surveystatusactivity.SurveyStatusMvpView;

import java.util.ArrayList;
import java.util.List;

public class SurveyDetailsAdapter extends RecyclerView.Adapter<SurveyDetailsAdapter.ItemBaseViewHolder> {
    private SurveyStatusMvpView statusMvpView;
    private ItemTouchHelperExtension mItemTouchHelperExtension;

    private ArrayList<SurveyEntity> surveyEntities = new ArrayList<>();

    public SurveyDetailsAdapter(SurveyStatusMvpView statusMvpView) {
        this.statusMvpView = statusMvpView;
    }

    public void setItemTouchHelperExtension(ItemTouchHelperExtension itemTouchHelperExtension) {
        mItemTouchHelperExtension = itemTouchHelperExtension;
    }


    @NonNull
    @Override
    public SurveyDetailsAdapter.ItemBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemMainBinding listItemMainBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item_main, parent, false);
        return new ItemSwipeWithActionWidthViewHolder(listItemMainBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull final SurveyDetailsAdapter.ItemBaseViewHolder holder, int position) {
        SurveyEntity farmerModel = surveyEntities.get(position);
        holder.listItemMainBinding.setData(farmerModel);
        // holder.listItemMainBinding.cartlayout.setData(farmerModel);

        if (farmerModel.getMapType() != null) {
            if (farmerModel.getMapType().equalsIgnoreCase("point")) {
                holder.listItemMainBinding.cartlayout.image.setBackgroundResource(R.drawable.new_point);
            } else if (farmerModel.getMapType().equalsIgnoreCase("line")) {
                holder.listItemMainBinding.cartlayout.image.setBackgroundResource(R.drawable.new_line);
            } else if (farmerModel.getMapType().equalsIgnoreCase("polygon")) {
                holder.listItemMainBinding.cartlayout.image.setBackgroundResource(R.drawable.new_polygon);
            }
        }

        if (!farmerModel.isUnchecked()) {
            holder.listItemMainBinding.cartlayout.checkBox.setChecked(true);
        } else {
            holder.listItemMainBinding.cartlayout.checkBox.setChecked(false);
        }

        holder.listItemMainBinding.cartlayout.checkBox.setOnClickListener(view -> statusMvpView.onListItemClicked(farmerModel));

        holder.listItemMainBinding.cartlayout.viewListMainContent.setOnClickListener(view -> {

        });
        if (holder instanceof ItemSwipeWithActionWidthViewHolder) {
            ItemSwipeWithActionWidthViewHolder viewHolder = (ItemSwipeWithActionWidthViewHolder) holder;
            viewHolder.mActionViewDelete.setOnClickListener(
                    view -> {
                        viewHolder.listItemMainBinding.viewListRepoActionContainer.setVisibility(View.GONE);
                        statusMvpView.onClickDeleteSurvey(farmerModel);
                        mItemTouchHelperExtension.closeOpened();
                    }
            );
            viewHolder.mActionViewEdit.setOnClickListener(
                    view -> {
                        viewHolder.listItemMainBinding.viewListRepoActionContainer.setVisibility(View.GONE);
                        statusMvpView.onClickEditSurvey(farmerModel);
                        mItemTouchHelperExtension.closeOpened();
                    }
            );
            viewHolder.mActionViewMapEdit.setOnClickListener(view ->
            {
                viewHolder.listItemMainBinding.viewListRepoActionContainer.setVisibility(View.GONE);
                statusMvpView.onClickPolygonMapEditSurvey(farmerModel, position);
                mItemTouchHelperExtension.closeOpened();
            });
        }
    }

    public void move(int from, int to) {
        SurveyEntity prev = surveyEntities.remove(from);
        surveyEntities.add(to > from ? to - 1 : to, prev);
        notifyItemMoved(from, to);
    }

    @Override
    public int getItemCount() {
        return surveyEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void addItems(List<SurveyEntity> blogList) {
        surveyEntities.clear();
        surveyEntities.addAll(blogList);
    }

    public List<SurveyEntity> getListData() {
        return surveyEntities;
    }

    public static class ItemBaseViewHolder extends RecyclerView.ViewHolder {
        public ListItemMainBinding listItemMainBinding;

        public ItemBaseViewHolder(ListItemMainBinding itemView) {
            super(itemView.getRoot());
            listItemMainBinding = itemView;
        }
    }


    class ItemSwipeWithActionWidthViewHolder extends ItemBaseViewHolder implements Extension {

        View mActionViewDelete;
        View mActionViewEdit;
        View mActionViewMapEdit;


        public ItemSwipeWithActionWidthViewHolder(ListItemMainBinding itemView) {
            super(itemView);
            mActionViewDelete = itemView.getRoot().findViewById(R.id.view_list_repo_action_delete);
            mActionViewEdit = itemView.getRoot().findViewById(R.id.view_list_repo_action_update);
            mActionViewMapEdit = itemView.getRoot().findViewById(R.id.map_edit_icon);
        }

        @Override
        public float getActionWidth() {
            listItemMainBinding.viewListRepoActionContainer.setVisibility(View.VISIBLE);
            postDelay();
            return listItemMainBinding.viewListRepoActionContainer.getWidth();
        }

    }

    private void postDelay() {
        Handler handler = new Handler();
        handler.postDelayed(this::notifyDataSetChanged, 100);
    }


//    private static final DiffUtil.ItemCallback<SurveyEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<SurveyEntity>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull SurveyEntity oldItem, @NonNull SurveyEntity newItem) {
//            return oldItem.getId() == newItem.getId();
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull SurveyEntity oldItem, @NonNull SurveyEntity newItem) {
//            if (oldItem.getName() != null && newItem.getName() != null) {
//                return oldItem.getName().equals(newItem.getName());
//            } else
//                return false;
//        }
//
//    };
//
//    private AsyncListDiffer<SurveyEntity> differ = new AsyncListDiffer<>(this, DIFF_CALLBACK);
//

}