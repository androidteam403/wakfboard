package com.thresholdsoft.praanadhara.ui.mainactivity.fragments.surveylistfrag.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.praanadhara.R;
import com.thresholdsoft.praanadhara.data.network.pojo.RowsEntity;
import com.thresholdsoft.praanadhara.databinding.AdapterSurveyListBinding;
import com.thresholdsoft.praanadhara.databinding.LmItemLoadingBinding;
import com.thresholdsoft.praanadhara.ui.mainactivity.fragments.surveylistfrag.SurveyListMvpPresenter;
import com.thresholdsoft.praanadhara.ui.mainactivity.fragments.surveylistfrag.SurveyListMvpView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<RowsEntity> surveyModelArrayList;
    private SurveyListMvpPresenter<SurveyListMvpView> mPresenter;
    private Activity activity;


    public SurveyAdapter(Activity activity, ArrayList<RowsEntity> surveyModelArrayList,
                         SurveyListMvpPresenter<SurveyListMvpView> mPresenter) {
        this.activity = activity;
        this.surveyModelArrayList = surveyModelArrayList;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public SurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        if (viewType == VIEW_TYPE_ITEM) {
            AdapterSurveyListBinding adapterSurveyListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.adapter_survey_list, parent, false);
            return new SurveyAdapter.ViewHolder(adapterSurveyListBinding);

//        else {
//            LmItemLoadingBinding lmItemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
//                    R.layout.lm_item_loading, parent, false);
//            return new SurveyAdapter.LoadingViewHolder(lmItemLoadingBinding);
//        }
    }


    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LmItemLoadingBinding lmItemLoadingBinding;

        public LoadingViewHolder(@NonNull LmItemLoadingBinding lmItemLoadingBinding) {
            super(lmItemLoadingBinding.getRoot());
            this.lmItemLoadingBinding = lmItemLoadingBinding;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final SurveyAdapter.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {

            RowsEntity farmerModel = surveyModelArrayList.get(position);
            holder.adapterSurveyListBinding.setSurvey(farmerModel);

            if (farmerModel.getFarmerLand().getSurveyLandLocation().getSubmitted().getUid() != null) {
                if (farmerModel.getFarmerLand().getSurveyLandLocation().getSubmitted().getUid() == null) {
                    holder.adapterSurveyListBinding.status.setText("New");
                    holder.adapterSurveyListBinding.takeSurveyText.setText("TAKE SURVEY");
                } else if (farmerModel.getFarmerLand().getSurveyLandLocation().getSubmitted().getUid().equalsIgnoreCase("yes")) {
                    holder.adapterSurveyListBinding.status.setText("Completed");
                    holder.adapterSurveyListBinding.takeSurveyText.setText("DONE");
                    holder.adapterSurveyListBinding.status.setTextColor(Color.parseColor("#0dbd00"));
                    holder.adapterSurveyListBinding.takeSurvey.setBackgroundResource(R.drawable.button_back_green);
                    holder.adapterSurveyListBinding.mainLayout.setBackgroundResource(R.drawable.adapter_survey_back_green);
                    holder.adapterSurveyListBinding.tick.setVisibility(View.VISIBLE);
                    String date = String.valueOf(farmerModel.getFarmerLand().getSurveyLandLocation().getSubmittedDate());
                    String suveyDate = String.valueOf(farmerModel.getFarmerLand().getSurveyLandLocation().getStartDate());
                    SimpleDateFormat spf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
                    Date newDate = null;
                    try {
                        newDate = spf.parse(date);
                        newDate = spf.parse(suveyDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    spf = new SimpleDateFormat("dd MMM yyyy");
                    date = spf.format(newDate);
                    suveyDate = spf.format(newDate);
                    holder.adapterSurveyListBinding.date.setVisibility(View.VISIBLE);
                    holder.adapterSurveyListBinding.date.setText(date);
                    holder.adapterSurveyListBinding.surveyDate.setVisibility(View.VISIBLE);
                    holder.adapterSurveyListBinding.surveyDate.setText(suveyDate);
                } else if (farmerModel.getFarmerLand().getSurveyLandLocation().getSubmitted().getUid().equalsIgnoreCase("no")) {
                    holder.adapterSurveyListBinding.status.setText("In Progress");
                    holder.adapterSurveyListBinding.takeSurveyText.setText("CONTINUE");
                    holder.adapterSurveyListBinding.status.setTextColor(Color.parseColor("#f79f37"));
                    holder.adapterSurveyListBinding.takeSurvey.setBackgroundResource(R.drawable.button_back_orange);
                    holder.adapterSurveyListBinding.mainLayout.setBackgroundResource(R.drawable.adapter_survey_back_orange);
                    String date = String.valueOf(farmerModel.getFarmerLand().getSurveyLandLocation().getStartDate());
                    SimpleDateFormat spf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
                    Date newDate = null;
                    try {
                        newDate = spf.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    spf = new SimpleDateFormat("dd MMM ,yyyy");
                    date = spf.format(newDate);
                    holder.adapterSurveyListBinding.date.setVisibility(View.VISIBLE);
                    holder.adapterSurveyListBinding.date.setText(date);
                }
            }

            holder.adapterSurveyListBinding.takeSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onItemClick(farmerModel);
                }
            });
            holder.adapterSurveyListBinding.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onItemClick(farmerModel);
                }
            });
        }
//        else if (holder instanceof LoadingViewHolder) {
//            showLoadingView((LoadingViewHolder) holder, position);
//        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AdapterSurveyListBinding adapterSurveyListBinding;

        public ViewHolder(@NonNull AdapterSurveyListBinding adapterSurveyListBinding) {
            super(adapterSurveyListBinding.getRoot());
            this.adapterSurveyListBinding = adapterSurveyListBinding;
        }
    }

    @Override
    public int getItemCount() {
        return surveyModelArrayList == null ? 0 : surveyModelArrayList.size();

    }

    @Override
    public int getItemViewType(int position) {
        return surveyModelArrayList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;

    }

    public void addItems(List<RowsEntity> blogList) {
        surveyModelArrayList.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        surveyModelArrayList.clear();
        notifyDataSetChanged();
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }
}
