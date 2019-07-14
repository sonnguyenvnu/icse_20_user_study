package com.example.jingbin.cloudreader.data.model;

import android.text.TextUtils;

import com.example.jingbin.cloudreader.app.ConstantsImageUrl;
import com.example.jingbin.cloudreader.bean.AndroidBean;
import com.example.jingbin.cloudreader.bean.FrontpageBean;
import com.example.jingbin.cloudreader.bean.GankIoDayBean;
import com.example.jingbin.cloudreader.http.HttpClient;
import com.example.jingbin.cloudreader.http.RequestImpl;
import com.example.jingbin.cloudreader.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jingbin on 2016/12/1.
 * �?日推�??model
 */
public class EverydayModel {

    private String year = "2016";
    private String month = "11";
    private String day = "24";
    private static final String HOME_ONE = "home_one";
    private static final String HOME_TWO = "home_two";
    private static final String HOME_SIX = "home_six";

    public void setData(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * 轮播图
     */
    public void showBannerPage(final RequestImpl listener) {
        Disposable subscribe = HttpClient.Builder.getTingServer().getFrontpage()
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<FrontpageBean>() {
                    @Override
                    public void accept(FrontpageBean frontpageBean) throws Exception {
                        if (listener != null) {
                            listener.loadSuccess(frontpageBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (listener != null) {
                            listener.loadFailed();
                        }
                    }
                });
        if (listener != null) {
            listener.addSubscription(subscribe);
        }
    }

    /**
     * 显示RecyclerView数�?�
     */
    public void showRecyclerViewData(final RequestImpl listener) {
        SPUtils.putString(HOME_ONE, "");
        SPUtils.putString(HOME_TWO, "");
        SPUtils.putString(HOME_SIX, "");
        Function<GankIoDayBean, Observable<List<List<AndroidBean>>>> function = new Function<GankIoDayBean, Observable<List<List<AndroidBean>>>>() {

            @Override
            public Observable<List<List<AndroidBean>>> apply(GankIoDayBean gankIoDayBean) throws Exception {

                List<List<AndroidBean>> lists = new ArrayList<>();
                GankIoDayBean.ResultsBean results = gankIoDayBean.getResults();

                if (results.getAndroid() != null && results.getAndroid().size() > 0) {
                    addUrlList(lists, results.getAndroid(), "Android");
                }
                if (results.getWelfare() != null && results.getWelfare().size() > 0) {
                    addUrlList(lists, results.getWelfare(), "�?利");
                }
                if (results.getiOS() != null && results.getiOS().size() > 0) {
                    addUrlList(lists, results.getiOS(), "IOS");
                }
                if (results.getRestMovie() != null && results.getRestMovie().size() > 0) {
                    addUrlList(lists, results.getRestMovie(), "休�?�视频");
                }
                if (results.getResource() != null && results.getResource().size() > 0) {
                    addUrlList(lists, results.getResource(), "拓展资�?");
                }
                if (results.getRecommend() != null && results.getRecommend().size() > 0) {
                    addUrlList(lists, results.getRecommend(), "瞎推�??");
                }
                if (results.getFront() != null && results.getFront().size() > 0) {
                    addUrlList(lists, results.getFront(), "�?端");
                }
                if (results.getApp() != null && results.getApp().size() > 0) {
                    addUrlList(lists, results.getApp(), "App");
                }

                return Observable.just(lists);
            }
        };

        Observer<List<List<AndroidBean>>> observer = new Observer<List<List<AndroidBean>>>() {

            @Override
            public void onError(Throwable e) {
                listener.loadFailed();
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {
                listener.addSubscription(d);
            }

            @Override
            public void onNext(List<List<AndroidBean>> lists) {
                listener.loadSuccess(lists);
            }
        };

        HttpClient.Builder.getGankIOServer().getGankIoDay(year, month, day)
                .flatMap(function)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
//        listener.addSubscription(disposable);
    }

    // subList没有实现�?列化�?缓存时会出错�?
    private void addUrlList(List<List<AndroidBean>> lists, List<AndroidBean> arrayList, String typeTitle) {
        // title
        AndroidBean bean = new AndroidBean();
        bean.setType_title(typeTitle);
        ArrayList<AndroidBean> androidBeen = new ArrayList<>();
        androidBeen.add(bean);
        lists.add(androidBeen);

        int androidSize = arrayList.size();

        if (androidSize > 0 && androidSize < 4) {

            lists.add(addUrlList(arrayList, androidSize));
        } else if (androidSize >= 4) {

            ArrayList<AndroidBean> list1 = new ArrayList<>();
            ArrayList<AndroidBean> list2 = new ArrayList<>();

            for (int i = 0; i < androidSize; i++) {
                if (i < 3) {
                    list1.add(getAndroidBean(arrayList, i, androidSize));
                } else if (i < 6) {
                    list2.add(getAndroidBean(arrayList, i, androidSize));
                }
            }
            lists.add(list1);
            lists.add(list2);
        }
    }

    private AndroidBean getAndroidBean(List<AndroidBean> arrayList, int i, int androidSize) {

        AndroidBean androidBean = new AndroidBean();
        // 标题
        androidBean.setDesc(arrayList.get(i).getDesc());
        // 类型
        androidBean.setType(arrayList.get(i).getType());
        // 跳转链接
        androidBean.setUrl(arrayList.get(i).getUrl());
        // �?机图的url
        if (i < 3) {
            androidBean.setImage_url(ConstantsImageUrl.HOME_SIX_URLS[getRandom(3)]);//三�?图
        } else if (androidSize == 4) {
            androidBean.setImage_url(ConstantsImageUrl.HOME_ONE_URLS[getRandom(1)]);//一图
        } else if (androidSize == 5) {
            androidBean.setImage_url(ConstantsImageUrl.HOME_TWO_URLS[getRandom(2)]);//两图
        } else if (androidSize >= 6) {
            androidBean.setImage_url(ConstantsImageUrl.HOME_SIX_URLS[getRandom(3)]);//三�?图
        }
        return androidBean;
    }


    private List<AndroidBean> addUrlList(List<AndroidBean> arrayList, int androidSize) {
        List<AndroidBean> tempList = new ArrayList<>();
        for (int i = 0; i < androidSize; i++) {
            AndroidBean androidBean = new AndroidBean();
            // 标题
            androidBean.setDesc(arrayList.get(i).getDesc());
            // 类型
            androidBean.setType(arrayList.get(i).getType());
            // 跳转链接
            androidBean.setUrl(arrayList.get(i).getUrl());
//            DebugUtil.error("---androidSize:  " + androidSize);
            // �?机图的url
            if (androidSize == 1) {
                androidBean.setImage_url(ConstantsImageUrl.HOME_ONE_URLS[getRandom(1)]);//一图
            } else if (androidSize == 2) {
                androidBean.setImage_url(ConstantsImageUrl.HOME_TWO_URLS[getRandom(2)]);//两图
            } else if (androidSize == 3) {
                androidBean.setImage_url(ConstantsImageUrl.HOME_SIX_URLS[getRandom(3)]);//三图
            }
            tempList.add(androidBean);
        }
        return tempList;
    }

    /**
     * �?��?�?�的�?机图，在�?次网络请求时�?置
     */
    private int getRandom(int type) {
        String saveWhere = null;
        int urlLength = 0;
        if (type == 1) {
            saveWhere = HOME_ONE;
            urlLength = ConstantsImageUrl.HOME_ONE_URLS.length;
        } else if (type == 2) {
            saveWhere = HOME_TWO;
            urlLength = ConstantsImageUrl.HOME_TWO_URLS.length;
        } else if (type == 3) {
            saveWhere = HOME_SIX;
            urlLength = ConstantsImageUrl.HOME_SIX_URLS.length;
        }

        String homeSix = SPUtils.getString(saveWhere, "");
        if (!TextUtils.isEmpty(homeSix)) {
            // 已�?�到的值
            String[] split = homeSix.split(",");

            Random random = new Random();
            for (int j = 0; j < urlLength; j++) {
                int randomInt = random.nextInt(urlLength);

                boolean isUse = false;
                for (String aSplit : split) {
                    if (!TextUtils.isEmpty(aSplit) && String.valueOf(randomInt).equals(aSplit)) {
                        isUse = true;
                        break;
                    }
                }
                if (!isUse) {
                    StringBuilder sb = new StringBuilder(homeSix);
                    sb.insert(0, randomInt + ",");
                    SPUtils.putString(saveWhere, sb.toString());
                    return randomInt;
                }
            }

        } else {
            Random random = new Random();
            int randomInt = random.nextInt(urlLength);
            SPUtils.putString(saveWhere, randomInt + ",");
            return randomInt;
        }
        return 0;
    }
}
