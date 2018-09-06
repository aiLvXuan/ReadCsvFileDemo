package me.lvxuan.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class District {
    private String name;    //番禺
    @SerializedName("city_id")
    private String cityId;    // CN101280102
    private String pinyin;
    private String latitude;
    private String longitude;
    @SerializedName("ad_code")
    private ArrayList<String> adCode;// 地址编码

    public District() {
    }

    public District(String name, String cityId, String pinyin, String latitude, String longitude, ArrayList<String> adCode) {
        this.name = name;
        this.cityId = cityId;
        this.pinyin = pinyin;
        this.latitude = latitude;
        this.longitude = longitude;
        this.adCode = adCode;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public ArrayList<String> getAdCode() {
        return adCode;
    }

    public void setAdCode(ArrayList<String> adCode) {
        this.adCode = adCode;
    }
}
