package com.bigkoo.convenientbannerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import me.relex.photodraweeview.OnViewTapListener;

/**
 * Created by Sai on 2018/4/26.
 */

public class AlbumActivity extends Activity implements OnViewTapListener {
    private ConvenientBanner convenientBanner;
    private ArrayList<String> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //下�?�fresco�?始化�?�懒写在这了
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_album);
        initViews();
        init();
    }

    private void initViews() {
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
    }

    private void init(){
        loadTestDatas();
        //图片例�?
        convenientBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public NetWorkImageHolderView createHolder(View itemView) {
                        return new NetWorkImageHolderView(itemView, AlbumActivity.this);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_photoview;
                    }
                }, images)
                //设置两个点图片作为翻页指示器，�?设置则没有指示器，�?�以根�?�自己需求自行�?�?�自己的指示器,�?需�?圆点指示器�?�用�?设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused});

    }

    /*
    加入测试Views
    * */
    private void loadTestDatas() {
        //图片�?�能过期哦，自己�?��?�测试�?�
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525319864&di=87f476652c96678547ccabbf112076be&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fgamephotolib%2F1410%2F27%2Fc0%2F40170771_1414341013392.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524714908870&di=9d43d35cefbabacdc879733aa7ddc82b&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D46de93bfc711728b24208461a095a9bb%2F4610b912c8fcc3ce5423d51d9845d688d43f2038.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524714935901&di=052557513540f3d740eeeb2439c585bb&imgtype=0&src=http%3A%2F%2Fwww.gzlco.com%2Fimggzl%2F214%2F1b6e6520ca474fe4bd3ff728817950717651.jpeg");
    }



    @Override
    public void onViewTap(View view, float x, float y) {
        //�?�击关闭
        finish();
    }
}
