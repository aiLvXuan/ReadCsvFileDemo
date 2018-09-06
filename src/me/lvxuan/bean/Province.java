package me.lvxuan.bean;

import java.util.ArrayList;

public class Province {
    private String name;
    private String pinyin;
    private ArrayList<City> cities;

    public Province() {
    }

    public Province(String name, String pinyin, ArrayList<City> cities) {
        this.name = name;
        this.pinyin = pinyin;
        this.cities = cities;
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

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
