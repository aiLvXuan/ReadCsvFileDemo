package me.lvxuan.bean;

import java.util.ArrayList;

public class City {
    private String name;    //广州
    private String pinyin;
    private ArrayList<District> districts;

    public City() {
    }

    public City(String name, String pinyin, ArrayList<District> districts) {
        this.name = name;
        this.pinyin = pinyin;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }
}
