package com.thresholdsoft.praanadhara.ui.surveystatusactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thresholdsoft.praanadhara.R;
import com.thresholdsoft.praanadhara.data.network.pojo.RowsEntity;
import com.thresholdsoft.praanadhara.databinding.ActivitySurveyStatusBinding;
import com.thresholdsoft.praanadhara.databinding.CustomActionbarBinding;
import com.thresholdsoft.praanadhara.ui.base.BaseActivity;
import com.thresholdsoft.praanadhara.ui.surveylistactivity.model.FarmersResponse;
import com.thresholdsoft.praanadhara.ui.surveystatusactivity.adapter.SurveyStatusAdapter;
import com.thresholdsoft.praanadhara.ui.surveytrack.SurveyTrackingActivity;
import com.thresholdsoft.praanadhara.ui.userlogin.UserLoginActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class SurveyStatusActivity extends BaseActivity implements SurveyStatusMvpView {
    @Inject
    SurveyStatusMvpPresenter<SurveyStatusMvpView> mpresenter;
    private ActivitySurveyStatusBinding activitySurveyStatusBinding;
    private SurveyStatusAdapter surveyStatusAdapter;
    private RowsEntity surveyModel;
    private ArrayList<RowsEntity> surveyModelArrayList = new ArrayList<>();
    CustomActionbarBinding customActionbarBinding;
    public static final int REQUEST_CODE = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySurveyStatusBinding = DataBindingUtil.setContentView(this, R.layout.activity_survey_status);
        getActivityComponent().inject(this);
        mpresenter.onAttach(SurveyStatusActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        Intent intent = getIntent();
        surveyModel = (RowsEntity) intent.getSerializableExtra("surveyData");
        surveyModelArrayList.add(surveyModel);
        surveyStatusAdapter = new SurveyStatusAdapter(this, surveyModelArrayList, this);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this);
        activitySurveyStatusBinding.recyclerSurveyStatus.setLayoutManager(mLayoutManager1);
        activitySurveyStatusBinding.recyclerSurveyStatus.setAdapter(surveyStatusAdapter);
        surveyStatusAdapter.notifyDataSetChanged();
        View includedLayout = findViewById(R.id.backArrow);
        ImageView insideTheIncludedLayout = (ImageView) includedLayout.findViewById(R.id.imageButton);
        insideTheIncludedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.left_right, R.anim.right_left);
            }
        });
    }

    @Override
    public void startSurvey(RowsEntity surveyModel) {
        mpresenter.startSurvey(surveyModel);

    }

    @Override
    public void startSurveySuccess(RowsEntity rowsEntity) {
        startActivityForResult(SurveyTrackingActivity.getIntent(this, rowsEntity),REQUEST_CODE);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {
            boolean requiredValue = data.getBooleanExtra("surveySubmit",false);
            if(requiredValue){
                Intent intent = getIntent();
                intent.putExtra("surveySubmit", requiredValue);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    @Override
    public void anotherizedToken() {
        Intent intent = new Intent(this, UserLoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
