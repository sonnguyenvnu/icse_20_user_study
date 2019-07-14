package com.example.jingbin.cloudreader.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jingbin.cloudreader.BR;
import com.example.jingbin.cloudreader.bean.moviechild.ImagesBean;
import com.example.jingbin.cloudreader.bean.moviechild.PersonBean;
import com.example.jingbin.cloudreader.bean.moviechild.RatingBean;

import java.util.List;

/**
 * Created by jingbin on 2016/12/9.
 */

public class MovieDetailBean extends BaseObservable {


    /**
     * rating : {"max":10,"average":6.9,"stars":"35","min":0}
     * reviews_count : 2440
     * wish_count : 22882
     * douban_site :
     * year : 2016
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2378133884.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2378133884.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2378133884.jpg"}
     * （更多信�?�）alt : https://movie.douban.com/subject/26630781/
     * id : 26630781
     * mobile_url : https://movie.douban.com/subject/26630781/mobile
     * title : 我�?是潘金莲
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/26630781
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/26630781/cinema/
     * episodes_count : null
     * countries : ["中国大陆"]
     * genres : ["剧情","喜剧"]
     * collect_count : 73047
     * casts : [{"alt":"https://movie.douban.com/celebrity/1050059/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1691.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1691.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1691.jpg"},"name":"范冰冰","id":"1050059"},{"alt":"https://movie.douban.com/celebrity/1274274/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/39703.jpg","large":"https://img3.doubanio.com/img/celebrity/large/39703.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/39703.jpg"},"name":"郭涛","id":"1274274"},{"alt":"https://movie.douban.com/celebrity/1324043/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/58870.jpg","large":"https://img3.doubanio.com/img/celebrity/large/58870.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/58870.jpg"},"name":"大�?","id":"1324043"},{"alt":"https://movie.douban.com/celebrity/1275044/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/28615.jpg","large":"https://img3.doubanio.com/img/celebrity/large/28615.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/28615.jpg"},"name":"张嘉译","id":"1275044"}]
     * current_season : null
     * original_title : 我�?是潘金莲
     * summary : 一个普通的农�?�妇女�?�雪莲（范冰冰 饰），为了纠正一�?��?，与上上下下�?方方�?��?�打了�??年交�?�。打交�?�的过程中，她没想到一件事�?��?了�?�一件事，接�?��?��?了第三件事。�??年过去，她没有把这�?��?纠正过�?�，但她饱�?了世间的人情冷暖，悟明白了�?�外一个�?��?�。�?�雪莲�?纠正的这�?��?是她�?夫说的。她�?夫说：你是�?�雪莲�?�？我咋觉得你是潘金莲呢？�?�雪莲�?告诉大家的是：我�?是潘金莲。©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1274255/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/45667.jpg","large":"https://img1.doubanio.com/img/celebrity/large/45667.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/45667.jpg"},"name":"冯�?刚","id":"1274255"}]
     * comments_count : 36102
     * ratings_count : 68772
     * aka : ["我是�?�雪莲","我�?��?�雪莲","I Am Not Madame Bovary"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private int do_count;
    private String share_url;
    private int seasons_count;
    private String schedule_url;
    private int episodes_count;
    private int collect_count;
    private String current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<PersonBean> casts;
    private List<PersonBean> directors;
    private List<String> aka;

    @Bindable
    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
        notifyPropertyChanged(BR.rating);
    }

    @Bindable
    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
        notifyPropertyChanged(BR.reviews_count);
    }

    @Bindable
    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
        notifyPropertyChanged(BR.wish_count);
    }

    @Bindable
    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
        notifyPropertyChanged(BR.douban_site);
    }

    @Bindable
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
        notifyPropertyChanged(BR.year);
    }

    @Bindable
    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
        notifyPropertyChanged(BR.images);
    }

    @Bindable
    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
        notifyPropertyChanged(BR.alt);
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
        notifyPropertyChanged(BR.mobile_url);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public int getDo_count() {
        return do_count;
    }

    public void setDo_count(int do_count) {
        this.do_count = do_count;
        notifyPropertyChanged(BR.do_count);
    }

    @Bindable
    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
        notifyPropertyChanged(BR.share_url);
    }

    @Bindable
    public int getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(int seasons_count) {
        this.seasons_count = seasons_count;
        notifyPropertyChanged(BR.seasons_count);
    }

    @Bindable
    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
        notifyPropertyChanged(BR.schedule_url);
    }

    @Bindable
    public int getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(int episodes_count) {
        this.episodes_count = episodes_count;
        notifyPropertyChanged(BR.episodes_count);
    }

    @Bindable
    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
        notifyPropertyChanged(BR.collect_count);
    }

    @Bindable
    public String getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(String current_season) {
        this.current_season = current_season;
        notifyPropertyChanged(BR.current_season);
    }

    @Bindable
    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
        notifyPropertyChanged(BR.original_title);
    }

    @Bindable
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
        notifyPropertyChanged(BR.summary);
    }

    @Bindable
    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
        notifyPropertyChanged(BR.subtype);
    }

    @Bindable
    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
        notifyPropertyChanged(BR.comments_count);
    }

    @Bindable
    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
        notifyPropertyChanged(BR.ratings_count);
    }

    @Bindable
    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
        notifyPropertyChanged(BR.countries);
    }

    @Bindable
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
        notifyPropertyChanged(BR.genres);
    }

    @Bindable
    public List<PersonBean> getCasts() {
        return casts;
    }

    public void setCasts(List<PersonBean> casts) {
        this.casts = casts;
        notifyPropertyChanged(BR.casts);
    }

    @Bindable
    public List<PersonBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PersonBean> directors) {
        this.directors = directors;
        notifyPropertyChanged(BR.directors);
    }

    @Bindable
    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
        notifyPropertyChanged(BR.aka);
    }
}
