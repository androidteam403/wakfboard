package com.thresholdsoft.praanadhara.data.network.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thresholdsoft.praanadhara.BR;

import java.io.Serializable;

public class SubmittedEntity extends BaseObservable implements Serializable {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("uid")
    private String uid;

    public String getName() {
        return name;
    }

    @Bindable
    public String getUid() {
        return uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUid(String uid) {
        this.uid = uid;
        notifyPropertyChanged(BR.uid);
    }
}
