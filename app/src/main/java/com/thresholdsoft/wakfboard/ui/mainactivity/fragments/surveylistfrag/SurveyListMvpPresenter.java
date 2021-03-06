package com.thresholdsoft.wakfboard.ui.mainactivity.fragments.surveylistfrag;

import androidx.lifecycle.LiveData;

import com.thresholdsoft.wakfboard.data.db.model.FarmerLands;
import com.thresholdsoft.wakfboard.data.db.model.SurveyStatusEntity;
import com.thresholdsoft.wakfboard.ui.base.MvpPresenter;

import java.util.List;

public interface SurveyListMvpPresenter<V extends SurveyListMvpView> extends MvpPresenter<V> {
    void onItemClick(FarmerLands farmerModel);


    void anotherizedTokenClearDate();

    void onClickNew();

    void onClickInProgress();

    void onClickCompleted();

    void pullToRefreshApiCall();

    void loadMoreApiCall(int i);

    void onStatusCountApiCall(boolean isPullToRefresh);

    LiveData<List<FarmerLands>> getAllFarmersLands();

    LiveData<SurveyStatusEntity> getSurveyStatusCount();

    void syncData();

    void onPropertyClick();
}
