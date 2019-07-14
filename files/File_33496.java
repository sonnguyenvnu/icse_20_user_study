package com.example.jingbin.cloudreader.bean.moviechild;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author jingbin
 */
public class FilmItemBean implements Serializable {


    /**
     * NearestCinemaCount : 24
     * NearestDay : 1557388800
     * NearestShowtimeCount : 27
     * aN1 : 瑞安·雷诺兹
     * aN2 : 贾斯�??斯·�?�密斯
     * actors : 瑞安·雷诺兹 / 贾斯�??斯·�?�密斯 / 凯瑟�?�·纽顿 / 雷佳音
     * cC : 50
     * commonSpecial : �?�贱皮�?�丘开�?��?�?�梦世界
     * d : 104
     * dN : 罗伯·莱特曼
     * def : 0
     * id : 235701
     * img : http://img5.mtime.cn/mt/2019/05/06/105806.73235069_1280X720X2.jpg
     * is3D : true
     * isDMAX : true
     * isFilter : false
     * isHasTrailer : true
     * isHot : false
     * isIMAX : false
     * isIMAX3D : true
     * isNew : false
     * isTicket : true
     * m :
     * movieId : 235701
     * movieType : 动作 / 冒险 / 喜剧
     * p : ["动作冒险喜剧"]
     * preferentialFlag : false
     * r : 7.1
     * rc : 0
     * rd : 20190510
     * rsC : 0
     * sC : 1144
     * t : 大侦探皮�?�丘
     * tCn : 大侦探皮�?�丘
     * tEn : Pokémon Detective Pikachu
     * ua : -1
     * versions : [{"enum":2,"version":"3D"},{"enum":4,"version":"IMAX3D"},{"enum":6,"version":"中国巨幕"}]
     * wantedCount : 2161
     * year : 2019
     */

    private String aN1;
    private String aN2;
    private String actors;
    private String commonSpecial;
    private String d;
    private String dN;
    private int id;
    private String img;
    private boolean is3D;
    private boolean isDMAX;
    private boolean isHot;
    private boolean isIMAX;
    private boolean isIMAX3D;
    private boolean isNew;
    private int movieId;
    private String movieType;
    // 制片国家
    private String locationName;
    private double r;
    private String rd;
    private int sC;
    private String t;
    private String tCn;
    private String tEn;
    private int wantedCount;
    private String year;
    private List<VersionsBean> versions;

    public String getLocationName() {
        if (!TextUtils.isEmpty(locationName)) {
            return "制片国家：" + locationName;
        } else {
            return "";
        }
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAN1() {
        return aN1;
    }

    public void setAN1(String aN1) {
        this.aN1 = aN1;
    }

    public String getAN2() {
        return aN2;
    }

    public void setAN2(String aN2) {
        this.aN2 = aN2;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getCommonSpecial() {
        return commonSpecial;
    }

    public void setCommonSpecial(String commonSpecial) {
        this.commonSpecial = commonSpecial;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDN() {
        return dN;
    }

    public void setDN(String dN) {
        this.dN = dN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isIsDMAX() {
        return isDMAX;
    }

    public void setIsDMAX(boolean isDMAX) {
        this.isDMAX = isDMAX;
    }

    public boolean isIsHot() {
        return isHot;
    }

    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    public boolean isIsIMAX() {
        return isIMAX;
    }

    public void setIsIMAX(boolean isIMAX) {
        this.isIMAX = isIMAX;
    }

    public boolean isIsIMAX3D() {
        return isIMAX3D;
    }

    public void setIsIMAX3D(boolean isIMAX3D) {
        this.isIMAX3D = isIMAX3D;
    }

    public boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public int getSC() {
        return sC;
    }

    public void setSC(int sC) {
        this.sC = sC;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getTCn() {
        return tCn;
    }

    public void setTCn(String tCn) {
        this.tCn = tCn;
    }

    public String getTEn() {
        return tEn;
    }

    public void setTEn(String tEn) {
        this.tEn = tEn;
    }

    public int getWantedCount() {
        return wantedCount;
    }

    public void setWantedCount(int wantedCount) {
        this.wantedCount = wantedCount;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<VersionsBean> getVersions() {
        return versions;
    }

    public void setVersions(List<VersionsBean> versions) {
        this.versions = versions;
    }

    public static class VersionsBean implements Serializable {
        /**
         * enum : 2
         * version : 3D
         */

        private String version;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
