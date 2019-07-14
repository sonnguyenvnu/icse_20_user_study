package com.bigkoo.convenientbannerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.PtrFrameLayoutCompat;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by Sai on 2018/4/28.
 */

public class HeaderActivity extends AppCompatActivity implements OnItemClickListener, BaseQuickAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    MyAdapter adapter;
    protected PtrFrameLayoutCompat ptrFrameLayout;

    ArrayList<String> datas = new ArrayList<>();
    ConvenientBanner convenientBanner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        setContentView(R.layout.activity_header);
        recyclerView = findViewById(R.id.recyclerView);
        for(int i = 0; i< 100; i++)
            datas.add("测试"+ i);
        adapter = new MyAdapter(R.layout.item_header_text,datas );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        View header = initHeader();
        adapter.addHeaderView(header);

        adapter.setOnItemClickListener(this);


            ptrFrameLayout = findViewById(R.id.ptrFrameLayout);
            ptrFrameLayout.setKeepHeaderWhenRefresh(true);
            ptrFrameLayout.disableWhenHorizontalMove(true);

            MaterialHeader headerView = new MaterialHeader(this);
        headerView.setColorSchemeColors(new int[]{ContextCompat.getColor(getApplicationContext(),R.color.colorAccent)});
        headerView.setPadding(0, 50, 0, 50);

            ptrFrameLayout.setHeaderView(headerView);
            ptrFrameLayout.setRatioOfHeaderHeightToRefresh(0.7f);
            ptrFrameLayout.addPtrUIHandler(headerView);
            PtrHandler ptrHandler = getPtrHandler();
            if (ptrHandler != null) {
                ptrFrameLayout.setPtrHandler(ptrHandler);
            }


    }

    private View initHeader() {

        View header =LayoutInflater.from(this).inflate(R.layout.item_covenientbanner_header,null);
        convenientBanner = (ConvenientBanner)header.findViewById(R.id.convenientBanner) ;

        loadTestDatas();
        //本地图片例�?
        convenientBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public LocalImageHolderView createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_localimage;
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，�?设置则没有指示器，�?�以根�?�自己需求自行�?�?�自己的指示器,�?需�?圆点指示器�?�用�?设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setOnItemClickListener(this);

        return header;
    }

    private void loadTestDatas() {
        //本地图片集�?�
        for (int position = 0; position < 7; position++)
            localImages.add(getResId("ic_test_" + position, R.drawable.class));

    }
    /**
     * 通过文件�??获�?�资�?id 例�?：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 开始自动翻页
    @Override
    protected void onResume() {
        super.onResume();
//        //开始自动翻页
        convenientBanner.startTurning();
    }

    // �?�止自动翻页
    @Override
    protected void onPause() {
        super.onPause();
//        //�?�止翻页
        convenientBanner.stopTurning();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,"点击了Banner第"+position+"个",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, AlbumActivity.class));

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(this,"点击了List第"+position+"个",Toast.LENGTH_SHORT).show();
    }

    public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tvText, item);
        }
    }

    PtrDefaultHandler ptrHandler;
    public PtrHandler getPtrHandler() {
        if(ptrHandler == null){
            ptrHandler = new PtrDefaultHandler() {
                @Override
                public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                }

                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    ptrFrameLayout.refreshComplete();
                }
            };
        }
        return ptrHandler;
    }

}
