package com.example.jingbin.cloudreader.ui.douban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.adapter.MovieDetailAdapter;
import com.example.jingbin.cloudreader.base.BaseHeaderActivity;
import com.example.jingbin.cloudreader.bean.MovieDetailBean;
import com.example.jingbin.cloudreader.bean.moviechild.SubjectsBean;
import com.example.jingbin.cloudreader.databinding.ActivityOneMovieDetailBinding;
import com.example.jingbin.cloudreader.databinding.HeaderSlideShapeBinding;
import com.example.jingbin.cloudreader.http.HttpClient;
import com.example.jingbin.cloudreader.utils.CommonUtils;
import com.example.jingbin.cloudreader.utils.StringFormatUtil;
import com.example.jingbin.cloudreader.view.webview.WebViewActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 最新的电影详情页：
 * 继承基类而写的电影详情页 2016-12-13
 */
public class OneMovieDetailActivity extends BaseHeaderActivity<HeaderSlideShapeBinding, ActivityOneMovieDetailBinding> {

    private SubjectsBean subjectsBean;
    private String mMoreUrl;
    private String mMovieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_movie_detail);
        if (getIntent() != null) {
            subjectsBean = (SubjectsBean) getIntent().getSerializableExtra("bean");
        }

        initSlideShapeTheme(setHeaderImgUrl(), setHeaderImageView());

        setTitle(subjectsBean.getTitle());
        setSubTitle(String.format("主演：%s", StringFormatUtil.formatName(subjectsBean.getCasts())));
//        ImageLoadUtil.showImg(bindingHeaderView.ivOnePhoto,subjectsBean.getImages().getLarge());
        bindingHeaderView.setSubjectsBean(subjectsBean);
        bindingHeaderView.executePendingBindings();

        loadMovieDetail();
    }

    @Override
    protected void setTitleClickMore() {
        WebViewActivity.loadUrl(OneMovieDetailActivity.this, mMoreUrl, mMovieName);
    }

    @Override
    protected int setHeaderLayout() {
        return R.layout.header_slide_shape;
    }

    @Override
    protected String setHeaderImgUrl() {
        if (subjectsBean == null) {
            return "";
        }
        return subjectsBean.getImages().getMedium();
    }

    @Override
    protected ImageView setHeaderImageView() {
        return bindingHeaderView.imgItemBg;
    }

    private void loadMovieDetail() {
        HttpClient.Builder.getDouBanService().getMovieDetail(subjectsBean.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetailBean>() {

                    @Override
                    public void onError(Throwable e) {
                        showError();
                    }

                    @Override
                    public void onComplete() {
                        showContentView();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(final MovieDetailBean movieDetailBean) {
                        // 上映日期
                        bindingHeaderView.tvOneDay.setText(String.format("上映日期：%s", movieDetailBean.getYear()));
                        // 制片国家
                        bindingHeaderView.tvOneCity.setText(String.format("制片国家/地区：%s", StringFormatUtil.formatGenres(movieDetailBean.getCountries())));
                        bindingHeaderView.setMovieDetailBean(movieDetailBean);
                        bindingContentView.setBean(movieDetailBean);
                        bindingContentView.executePendingBindings();

                        mMoreUrl = movieDetailBean.getAlt();
                        mMovieName = movieDetailBean.getTitle();

                        transformData(movieDetailBean);
                    }
                });
    }

    /**
     * 异步线程转�?�数�?�
     */
    private void transformData(final MovieDetailBean movieDetailBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < movieDetailBean.getDirectors().size(); i++) {
                    movieDetailBean.getDirectors().get(i).setType("导演");
                }
                for (int i = 0; i < movieDetailBean.getCasts().size(); i++) {
                    movieDetailBean.getCasts().get(i).setType("演员");
                }

                OneMovieDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter(movieDetailBean);
                    }
                });
            }
        }).start();
    }

    /**
     * 设置导演&演员adapter
     */
    private void setAdapter(MovieDetailBean movieDetailBean) {
        bindingContentView.xrvCast.setVisibility(View.VISIBLE);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(OneMovieDetailActivity.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bindingContentView.xrvCast.setLayoutManager(mLayoutManager);
        bindingContentView.xrvCast.setPullRefreshEnabled(false);
        bindingContentView.xrvCast.setLoadingMoreEnabled(false);
        // 需加，�?然滑动�?�?畅
        bindingContentView.xrvCast.setNestedScrollingEnabled(false);
        bindingContentView.xrvCast.setHasFixedSize(false);

        MovieDetailAdapter mAdapter = new MovieDetailAdapter();
        mAdapter.addAll(movieDetailBean.getDirectors());
        mAdapter.addAll(movieDetailBean.getCasts());
        bindingContentView.xrvCast.setAdapter(mAdapter);
    }

    @Override
    protected void onRefresh() {
        loadMovieDetail();
    }

    /**
     * @param context      activity
     * @param positionData bean
     * @param imageView    imageView
     */
    public static void start(Activity context, SubjectsBean positionData, ImageView imageView) {
        Intent intent = new Intent(context, OneMovieDetailActivity.class);
        intent.putExtra("bean", positionData);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView, CommonUtils.getString(R.string.transition_movie_img));//与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

}
