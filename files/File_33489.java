package com.example.jingbin.cloudreader.bean.book;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.http.ParamNames;
import com.example.jingbin.cloudreader.BR;
import com.example.jingbin.cloudreader.bean.moviechild.ImagesBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jingbin on 2016/12/25.
 */

public class BookDetailBean extends BaseObservable implements Serializable{

    /**
     * rating : {"max":10,"numRaters":72,"average":"7.3","min":0}
     * subtitle : VAN LOON'S GEOGRAPHY
     * author : ["（美）房龙　著，王希�?�　译"]
     * pubdate : 2008-9
     * tags : [{"count":33,"name":"地�?�","title":"地�?�"},{"count":24,"name":"房龙","title":"房龙"},{"count":8,"name":"世界地�?�","title":"世界地�?�"},{"count":6,"name":"综�?�","title":"综�?�"},{"count":4,"name":"世界","title":"世界"},{"count":4,"name":"科普","title":"科普"},{"count":4,"name":"社会学","title":"社会学"},{"count":3,"name":"人文","title":"人文"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s9007440.jpg
     * binding :
     * translator : ["王希�?�"]
     * catalog : 一　人类与家园二　什么是“地�?�学�?三　地�?�的特点�?规律和状况四　地图：万水�?�山寻路难五　地�?�有四季六　海洋中的大陆七　�?�现欧洲八　希腊：连接�?��?亚洲和新兴欧洲的桥�?�?　�?大利：地�?�造就的海上霸主或陆上强国�??　西�?�牙：�?�洲与欧洲交锋之地�??一　法国：应有尽有的国家�??二　比利时：几页文件决定了它的命�?�??三  �?�森堡：�?��?�历�?�的�?�弄�??四  瑞士：四个语言�?�?�的民�?和�?�相处�??五　德国：建国太迟的国家�??六  奥地利：无人�?彩的国家�??七  丹麦：�?国在�?些方�?�胜过大国的典范�??八　冰岛：北冰洋上一个有趣的政治实验室�??�?　斯堪的纳维亚�?�岛：瑞典王国和挪�?王国的属地二�??　�?�兰：沼泽上崛起的�?国二�??一  英国：�?�?岛国人满为患二�??二  俄罗斯：欧洲之国还是亚洲之国二�??三  波兰：自家的土地别人的走廊二�??四  �?�克斯洛�?克：《凡尔赛和约》的果实二�??五  �?�斯拉夫：《凡尔赛和约》的�?�一件作�?二�??六  �?加利亚：最正统的巴尔干国家二�??七  罗马尼亚：一个有石油有王室的国家二�??八  匈牙利：或者匈牙利的残余二�??�?　芬兰：勤劳和智慧战胜�?�劣环境的�?�一明�?三�??　�?�现亚洲三�??一　亚洲与世界三�??二  亚洲中部高原三�??三　亚洲西部高原三�??四　阿拉伯三�??五  �?�度：人和自然相互促进，共�?��?�展三�??六  亚洲�?�部�?�岛的主人三�??七　中国：东亚大�?�岛三�??八　�?鲜与蒙�?�：�?途未�?�三�??�?　日本：野心勃勃的岛国四�??　�?�律宾：原墨西哥的领地四�??一　�?�属东�?�度群岛：�?人物掌大�?�四�??二  澳大利亚：造物主的�?�?之作四�??三　新西兰：�?�瑚岛屿的王国四�??四  太平洋群岛：�?耕�?织，照样生活四�??五  �?�洲：矛盾�?�?的大陆四�??六　美洲：最幸�?的大陆四�??七　创造新世界
     * pages : 308
     * images : {"small":"https://img3.doubanio.com/spic/s9007440.jpg","large":"https://img3.doubanio.com/lpic/s9007440.jpg","medium":"https://img3.doubanio.com/mpic/s9007440.jpg"}
     * alt : https://book.douban.com/subject/3235564/
     * id : 3235564
     * publisher : 北京出版社
     * isbn10 : 7200073261
     * isbn13 : 9787200073263
     * title : 地�?�的故事
     * url : https://api.douban.com/v2/book/3235564
     * alt_title :
     * author_intro : 房龙，�?�裔美国人。他是一�?�?艺�?��?的�?�学之士。房龙的人生�?历异常丰富，曾�?从事过�?��?�?�样的工作，先�?�当过教师�?编辑�?记者和播音员。他一生创作了大�?饮誉世界的作�?。在写作中，他善于�?用生动活泼的文字，撰写通俗易懂的历�?�著作。自20世纪20年代起，凡是他�?�表的作�?，都在美国畅销一空，并被译�?多�?文字在世界�?�国出版�?�行，深�?��?�国年轻读者的喜爱。在他众多的畅销书中，就包括这部独树一帜的地�?�学著作--《地�?�的故事》。瓣 房龙的这部著作�?�?了其惯有的行文风格。他用诙�?幽默的文字把枯燥的地�?�知识�??述得活�?�活现，使读者在轻�?�愉快之际�?仅了解了人类漫长历�?��?�展的�?�龙去脉，且能在掩�?�之�?�获得�?少�?��?�。世界地�?�在房龙的笔下，既�?�气象风云的亘�?��?��?，也�?�沧海桑田的物�?�星移。他所写的地�?�，是一部有血有肉的“人的�?地�?�。因为他�?�信，世界上任何一�?�土地的�?�?性都�?�决于这�?�土地上的人民以科学�?商业�?宗教或�?�?艺术形�?为全人类的幸�?所作出的或大或�?的贡献。
     * 为什么丹麦人�??好�?�谧的书斋，而西�?�牙人则热衷于广阔的天地?为什么日本总是�?�方百计想�?扩张，而瑞士则想方设法追求中立?为什么亚洲国家总是安于现状，而欧洲国家�?�总是强调改�?�?一个国家的民�?性格和历�?��?�展与其地�?�因素究竟有何关�?�?房龙在这部书中给出了他自己的答案。
     * 房龙在本书中摒弃了枯燥�?味的科普说教和传统填鸭�?的内容�?�输，而是以一�?清新活泼的方�?讲述世界地�?�知识，从而激�?�读者的阅读兴趣，让地�?�知识�?�得生动有趣。与此�?�时，他在书中对一部分国家的地�?�环境进行了浓�?的�??述，并从中分�?出地�?�对一个国家的历�?�演�?�和一个民�?的性格形�?所产生的影�?。
     * summary : 沧海桑田�?物�?�星移，几度风雨�?几度春秋，地�?��?��?永无止歇。然而，这�?��?展现的仅仅是一�?自风情�?�？当然�?是。在房龙的笔下，世界地�?�远�?�如此，它是一部有血有肉的“人的�?地�?�。在这部地�?�学著作中，房龙以幽默�?�智的文风，用一个个�?故事，将�?个国家的民�?性格�?历�?��?�展与地�?�环境的关�?�娓娓而�?�，为读者打开了从�?�一个角度看世界的窗户，使枯燥的地�?�知识�?�?�?味。跟�?�?�这�?伟大的文化传播者和出色的通俗读物作家的笔触，读者既能轻�?�愉快地了解人类漫长历�?�的�?�龙去脉，也会在掩�?�之�?�回味沉�?，久久�?�?释�?�。
     * --------------------------------------------------------------------------------
     * 一人类与家园
     * 二什么是“地�?�学�?
     * 三地�?�的特点�?规律和状况
     * 四地图：万水�?�山寻路难
     * 五地�?�有四季
     * 六海洋中的大陆
     * 七�?�现欧洲
     * 八希腊：连接�?��?亚洲和新兴欧洲的桥�?
     * �?�?大利：地�?�造就的海上霸主或陆上强国
     * �??西�?�牙：�?�洲与欧洲交锋之地
     * �??一法国：应有尽有的国家
     * �??二比利时：几页文件决定了它的命�?
     * �??三�?�森堡：�?��?�历�?�的�?�弄
     * �??四瑞士：四个语言�?�?�的民�?和�?�相处
     * �??五德国：建国太迟的国家
     * �??六奥地利：无人�?彩的国家
     * �??七丹麦：�?国在�?些方�?�胜过大国的典范
     * �??八冰岛：北冰洋上一个有趣的政治实验室
     * �??�?斯堪的纳维亚�?�岛：瑞典王国和挪�?王国的属地
     * 二�??�?�兰：沼泽上崛起的�?国
     * 二�??一英国：�?�?岛国人满为患
     * 二�??二俄罗斯：欧洲之国还是亚洲之国
     * 二�??三波兰：自家的土地别人的走廊
     * 二�??四�?�克斯洛�?克：《凡尔赛和约》的果实
     * 二�??五�?�斯拉夫：《凡尔赛和约》的�?�一件作�?
     * 二�??六�?加利亚：最正统的巴尔干国家
     * 二�??七罗马尼亚：一个有石油有王室的国家
     * 二�??八匈牙利：或者匈牙利的残余
     * 二�??�?芬兰：勤劳和智慧战胜�?�劣环境的�?�一明�?
     * 三�??�?�现亚洲
     * 三�??一亚洲与世界
     * 三�??二亚洲中部高原
     * 三�??三亚洲西部高原
     * 三�??四阿拉伯
     * 三�??五�?�度：人和自然相互促进，共�?��?�展
     * 三�??六亚洲�?�部�?�岛的主人
     * 三�??七中国：东亚大�?�岛
     * 三�??八�?鲜与蒙�?�：�?途未
     * 三�??�?日本：野心勃勃的岛国
     * 四�??�?�律宾：原墨西哥的领地
     * 四�??一�?�属东�?�度群岛：�?人物掌大�?�
     * 四�??二澳大利亚：造物主的�?�?之作
     * 四�??三新西兰：�?�瑚岛屿的王国
     * 四�??四太平洋群岛：�?耕�?织，照样生活
     * 四�??五�?�洲：矛盾�?�?的大陆
     * 四�??六美洲：最幸�?的大陆
     * 四�??七创措新世界
     * price : 23.90元
     */
    @ParamNames("rating")
    private BooksBean.RatingBean rating;
    @ParamNames("subtitle")
    private String subtitle;
    @ParamNames("pubdate")
    private String pubdate;
    @ParamNames("origin_title")
    private String origin_title;
    @ParamNames("image")
    private String image;
    @ParamNames("binding")
    private String binding;
    @ParamNames("catalog")
    private String catalog;
    @ParamNames("pages")
    private String pages;
    @ParamNames("images")
    private ImagesBean images;
    @ParamNames("alt")
    private String alt;
    @ParamNames("id")
    private String id;
    @ParamNames("publisher")
    private String publisher;
    @ParamNames("isbn10")
    private String isbn10;
    @ParamNames("isbn13")
    private String isbn13;
    @ParamNames("title")
    private String title;
    @ParamNames("url")
    private String url;
    @ParamNames("alt_title")
    private String alt_title;
    @ParamNames("author_intro")
    private String author_intro;
    @ParamNames("summary")
    private String summary;
    @ParamNames("price")
    private String price;
    @ParamNames("author")
    private List<String> author;
    @ParamNames("tags")
    private List<BooksBean.TagsBean> tags;
    @ParamNames("translator")
    private List<String> translator;

    @Bindable
    public BooksBean.RatingBean getRating() {
        return rating;
    }

    public void setRating(BooksBean.RatingBean rating) {
        this.rating = rating;
        notifyPropertyChanged(BR.rating);
    }

    @Bindable
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        notifyPropertyChanged(BR.subtitle);
    }

    @Bindable
    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
        notifyPropertyChanged(BR.pubdate);
    }

    @Bindable
    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
        notifyPropertyChanged(BR.origin_title);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
        notifyPropertyChanged(BR.binding);
    }

    @Bindable
    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
        notifyPropertyChanged(BR.catalog);
    }

    @Bindable
    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
        notifyPropertyChanged(BR.pages);
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
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
        notifyPropertyChanged(BR.publisher);
    }

    @Bindable
    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
        notifyPropertyChanged(BR.isbn10);
    }

    @Bindable
    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        notifyPropertyChanged(BR.isbn13);
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
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
        notifyPropertyChanged(BR.alt_title);
    }

    @Bindable
    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
        notifyPropertyChanged(BR.author_intro);
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
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
        notifyPropertyChanged(BR.author);
    }

    @Bindable
    public List<BooksBean.TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<BooksBean.TagsBean> tags) {
        this.tags = tags;
        notifyPropertyChanged(BR.tags);
    }

    @Bindable
    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
        notifyPropertyChanged(BR.translator);
    }

}
