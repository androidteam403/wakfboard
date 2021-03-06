package com.thresholdsoft.wakfboard.ui.surveystatusactivity.dialog.deletedialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.thresholdsoft.wakfboard.R;
import com.thresholdsoft.wakfboard.databinding.DialogDeleteBinding;
import com.thresholdsoft.wakfboard.databinding.SurveyPointDialogBinding;
import com.thresholdsoft.wakfboard.ui.surveytrack.SurveyTrackMvpView;


public class DeleteDialog {

    private Dialog dialog;
    private DialogDeleteBinding editQuantityDialogBinding;
    private SurveyTrackMvpView surveyTrackMvpView;

    private boolean negativeExist = false;

    public DeleteDialog(Context context) {
        dialog = new Dialog(context);
        editQuantityDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_delete, null, false);
        dialog.setContentView(editQuantityDialogBinding.getRoot());
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        editQuantityDialogBinding.dialogButtonOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SurveyDetailsModel surveyDetailsModel = new SurveyDetailsModel();
//                surveyDetailsModel.setSurveyName(editQuantityDialogBinding.editNameEditText.getText().toString());
//                surveyTrackMvpView.onPassSurveyTrackEnteredDetails(surveyDetailsModel);
//
//            }
//        });
    }

    public void setEditTextDialogDetails(SurveyTrackMvpView surveyTrackMvpView) {
        this.surveyTrackMvpView = surveyTrackMvpView;
    }

    public void setPositiveListener(View.OnClickListener okListener) {
        editQuantityDialogBinding.dialogButtonOK.setOnClickListener(okListener);
    }


    public void setNegativeListener(View.OnClickListener okListener) {
        editQuantityDialogBinding.dialogButtonNO.setOnClickListener(okListener);
    }

    public void show() {
        if (negativeExist) {
            editQuantityDialogBinding.dialogButtonNO.setVisibility(View.VISIBLE);
            editQuantityDialogBinding.separator.setVisibility(View.VISIBLE);
        } else {
            editQuantityDialogBinding.dialogButtonNO.setVisibility(View.GONE);
            editQuantityDialogBinding.separator.setVisibility(View.GONE);
        }
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setTitle(String title) {
        editQuantityDialogBinding.title.setText(title);
    }


    public void setPositiveLabel(String positive) {
        editQuantityDialogBinding.dialogButtonOK.setText(positive);
    }

    public void setNegativeLabel(String negative) {
        negativeExist = true;
        editQuantityDialogBinding.dialogButtonNO.setText(negative);
    }

    public String getPointName() {
//        return editQuantityDialogBinding.editNameEditText.getText().toString();
        return null;
    }

    public String getPointDescription() {
//        return editQuantityDialogBinding.editDescriptionEditText.getText().toString();
        return null;
    }
}
