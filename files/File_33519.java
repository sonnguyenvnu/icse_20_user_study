package com.example.jingbin.cloudreader.data.model;

import com.example.jingbin.cloudreader.bean.GankIoDataBean;
import com.example.jingbin.cloudreader.http.HttpClient;
import com.example.jingbin.cloudreader.http.RequestImpl;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jingbin on 2017/1/17.
 * 分类数�?�: http://gank.io/api/data/数�?�类型/请求个数/第几页  的Model
 * 好处之一是请求数�?�接�?��?�以统一，�?用�?个地方都写请求的接�?�，更�?�接�?�方便。
 * 其实代�?�?也没有�?少多少，但维护起�?�方便。
 */

public class GankOtherModel {

    private String id;
    private int page;
    private int perPage;

    public void setData(String id, int page, int perPage) {
        this.id = id;
        this.page = page;
        this.perPage = perPage;
    }

    public void getGankIoData(final RequestImpl listener) {
        HttpClient.Builder.getGankIOServer().getGankIoData(id, page, perPage)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIoDataBean>() {

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
                    public void onNext(GankIoDataBean gankIoDataBean) {
                        listener.loadSuccess(gankIoDataBean);
                    }
                });
    }
}
