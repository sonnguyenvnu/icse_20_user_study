package com.example.jingbin.cloudreader.http.api;

import java.util.List;
import java.util.Random;

public class MusicApiUtils {


    public static String Music_Titles []={"�?行","�?典","韩系","欧美"};
    public static String PopulayTag[] ={"歌声", "�?�春", "回忆", "孙燕姿","周�?�伦","林俊�?�", "陈奕迅", "王力�?", "邓紫棋","风声",  "海边", "童�?", "美女",  "一生", "爱", "爱情", "远方", "缘分","天空","张国�?�","黄家驹","　beyond", "黑豹�?队" };
    public static String ClassicTag []={"张国�?�","黄家驹","　beyond", "黑豹�?队", "王�?�", "五月天", "陈奕迅", "�?�巨基", "�?��?�嬅", "�?�倩文", "许嵩","刘德�?�","邓丽�?�","张学�?�"};
    public static String KereaTag[] ={"bigbang","rain", "PSY", "�?�弘基", "�?�承哲","金钟国", "�?��?利", "�?�?�", "IU", "EXO", "T-ara", "东方神起", "Epik High", "Girl's Day", " 紫雨林", "Zebra"};
    public static String AmericanTag[] ={"Jay-Z","Justin Bieber","James Blunt","Eminem","Akon","Adele","Avril Lavigne","Beyoncé","Lady GaGa","Taylor Swift","Alicia Keys","Owl City","Coldplay"};


    public static String[] getApiTag(int pos){
        switch (pos){
            case 0:
                return PopulayTag;
            case 1:
                return ClassicTag;
            case 2:
                return KereaTag;
            case 3:
                return AmericanTag;



        }
        return null;
    }


    public static  String getRandomTAG(List<String> list){
        Random random=new Random();
        int i=random.nextInt(list.size());
        return  list.get(i);
    }
}
