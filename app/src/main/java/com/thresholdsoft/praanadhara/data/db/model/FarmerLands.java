package com.thresholdsoft.praanadhara.data.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.thresholdsoft.praanadhara.data.utils.DateConverter;

@Entity(tableName = "farmer_land")
@TypeConverters({DateConverter.class})
public class FarmerLands {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "uid")
    private String mUid;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "mobile")
    private String mMobile;

    @ColumnInfo(name = "email")
    private String mEmail;

    @ColumnInfo(name = "pic_path")
    private String mPicPath;

    @ColumnInfo(name = "farmer_land_uid")
    private String mFarmerLandUid;

    @ColumnInfo(name = "pincode")
    private String mPincode;

    @ColumnInfo(name = "village")
    private String mVillage;

    public FarmerLands(String mUid, String mName, String mMobile, String mEmail, String mPicPath, String mFarmerLandUid, String mPincode, String mVillage) {
        this.mUid = mUid;
        this.mName = mName;
        this.mMobile = mMobile;
        this.mEmail = mEmail;
        this.mPicPath = mPicPath;
        this.mFarmerLandUid = mFarmerLandUid;
        this.mPincode = mPincode;
        this.mVillage = mVillage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String mUid) {
        this.mUid = mUid;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mMobile) {
        this.mMobile = mMobile;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPicPath() {
        return mPicPath;
    }

    public void setPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }

    public String getFarmerLandUid() {
        return mFarmerLandUid;
    }

    public void setFarmerLandUid(String mFarmerLandUid) {
        this.mFarmerLandUid = mFarmerLandUid;
    }

    public String getPincode() {
        return mPincode;
    }

    public void setPincode(String mPincode) {
        this.mPincode = mPincode;
    }

    public String getVillage() {
        return mVillage;
    }

    public void setVillage(String mVillage) {
        this.mVillage = mVillage;
    }
}
