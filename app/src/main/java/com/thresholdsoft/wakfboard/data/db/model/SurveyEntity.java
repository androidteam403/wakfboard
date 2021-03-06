package com.thresholdsoft.wakfboard.data.db.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.thresholdsoft.wakfboard.BR;
import com.thresholdsoft.wakfboard.data.utils.DateConverter;

import java.io.Serializable;

@Entity(tableName = "survey_details")
@TypeConverters({DateConverter.class})
public class SurveyEntity extends BaseObservable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "land_uid")
    private String landUid;

    @ColumnInfo(name = "uid")
    private String uid;

    @ColumnInfo(name = "start_uid")
    private String startUid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "latLongs")
    private String latLongs;

    @ColumnInfo(name = "map_type")
    private String mapType;

    @ColumnInfo(name = "isEdit")
    private boolean isEdit;

    @ColumnInfo(name = "isDelete")
    private boolean isDelete;

    @ColumnInfo(name = "isSync")
    private boolean isSync;

    public SurveyEntity(String landUid, String uid, String startUid, String name, String description, String latLongs, String mapType, boolean isSync) {
        this.landUid = landUid;
        this.uid = uid;
        this.startUid = startUid;
        this.name = name;
        this.description = description;
        this.latLongs = latLongs;
        this.mapType = mapType;
        this.isSync = isSync;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLandUid() {
        return landUid;
    }

    public void setLandUid(String landUid) {
        this.landUid = landUid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStartUid() {
        return startUid;
    }

    public void setStartUid(String startUid) {
        this.startUid = startUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatLongs() {
        return latLongs;
    }

    public void setLatLongs(String latLongs) {
        this.latLongs = latLongs;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    private boolean isUnchecked;

    @Bindable
    public boolean isUnchecked() {
        return isUnchecked;
    }

    public void setUnchecked(boolean unchecked) {
        isUnchecked = unchecked;
        notifyPropertyChanged(BR.unchecked);
    }
}
