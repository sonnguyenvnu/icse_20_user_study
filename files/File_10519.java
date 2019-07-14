package com.vondear.rxdemo.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vondear.rxdemo.R;
import com.vondear.rxdemo.adapter.AdapterLeftMenu;
import com.vondear.rxdemo.adapter.AdapterRightDish;
import com.vondear.rxdemo.interfaces.ShopCartInterface;
import com.vondear.rxdemo.model.ModelDish;
import com.vondear.rxdemo.model.ModelDishMenu;
import com.vondear.rxdemo.model.ModelShopCart;
import com.vondear.rxdemo.view.RxDialogShopCart;
import com.vondear.rxdemo.view.RxFakeAddImageView;
import com.vondear.rxdemo.view.RxPointFTypeEvaluator;
import com.vondear.rxui.activity.ActivityBase;
import com.vondear.rxui.view.RxTitle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author vondear
 */
public class ActivityELMe extends ActivityBase implements AdapterLeftMenu.onItemSelectedListener, ShopCartInterface, RxDialogShopCart.ShopCartDialogImp {
    private final static String TAG = "MainActivity";
    @BindView(R.id.rx_title)
    RxTitle mRxTitle;
    @BindView(R.id.shopping_cart_total_tv)
    TextView totalPriceTextView;
    @BindView(R.id.shopping_cart_bottom)
    LinearLayout mShoppingCartBottom;
    @BindView(R.id.left_menu)
    RecyclerView mLeftMenu;//å·¦ä¾§è?œå?•æ ?
    @BindView(R.id.right_menu)
    RecyclerView mRightMenu;//å?³ä¾§è?œå?•æ ?
    @BindView(R.id.right_menu_tv)
    TextView headerView;
    @BindView(R.id.right_menu_item)
    LinearLayout headerLayout;//å?³ä¾§è?œå?•æ ?æœ€ä¸Šé?¢çš„è?œå?•
    @BindView(R.id.shopping_cart)
    ImageView mShoppingCart;
    @BindView(R.id.shopping_cart_layout)
    FrameLayout mShoppingCartLayout;
    @BindView(R.id.shopping_cart_total_num)
    TextView totalPriceNumTextView;
    @BindView(R.id.main_layout)
    RelativeLayout mMainLayout;
    private ModelDishMenu headMenu;
    private AdapterLeftMenu leftAdapter;
    private AdapterRightDish rightAdapter;
    private ArrayList<ModelDishMenu> mModelDishMenuList;//æ•°æ?®æº?
    private boolean leftClickType = false;//å·¦ä¾§è?œå?•ç‚¹å‡»å¼•å?‘çš„å?³ä¾§è?”åŠ¨
    private ModelShopCart mModelShopCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elme);
        ButterKnife.bind(this);
        mRxTitle.setLeftFinish(mContext);
        initData();
        initView();
        initAdapter();
    }

    private void initView() {
        mLeftMenu.setLayoutManager(new LinearLayoutManager(this));
        mRightMenu.setLayoutManager(new LinearLayoutManager(this));

        mRightMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1) == false) {//æ— æ³•ä¸‹æ»‘
                    showHeadView();
                    return;
                }
                View underView = null;
                if (dy > 0) {
                    underView = mRightMenu.findChildViewUnder(headerLayout.getX(), headerLayout.getMeasuredHeight() + 1);
                } else {
                    underView = mRightMenu.findChildViewUnder(headerLayout.getX(), 0);
                }
                if (underView != null && underView.getContentDescription() != null) {
                    int position = Integer.parseInt(underView.getContentDescription().toString());
                    ModelDishMenu menu = rightAdapter.getMenuOfMenuByPosition(position);

                    if (leftClickType || !menu.getMenuName().equals(headMenu.getMenuName())) {
                        if (dy > 0 && headerLayout.getTranslationY() <= 1 && headerLayout.getTranslationY() >= -1 * headerLayout.getMeasuredHeight() * 4 / 5 && !leftClickType) {// underView.getTop()>9
                            int dealtY = underView.getTop() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        } else if (dy < 0 && headerLayout.getTranslationY() <= 0 && !leftClickType) {
                            headerView.setText(menu.getMenuName());
                            int dealtY = underView.getBottom() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        } else {
                            headerLayout.setTranslationY(0);
                            headMenu = menu;
                            headerView.setText(headMenu.getMenuName());
                            for (int i = 0; i < mModelDishMenuList.size(); i++) {
                                if (mModelDishMenuList.get(i) == headMenu) {
                                    leftAdapter.setSelectedNum(i);
                                    break;
                                }
                            }
                            if (leftClickType) {
                                leftClickType = false;
                            }
                            Log.e(TAG, "onScrolled: " + menu.getMenuName());
                        }
                    }
                }
            }
        });

        mShoppingCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCart(view);
            }
        });
    }

    private void initData() {
        mModelShopCart = new ModelShopCart();
        mModelDishMenuList = new ArrayList<>();
        ArrayList<ModelDish> dishs1 = new ArrayList<>();
        dishs1.add(new ModelDish("é?¢åŒ…", 1.0, 10));
        dishs1.add(new ModelDish("è›‹æŒž", 1.0, 10));
        dishs1.add(new ModelDish("ç‰›å¥¶", 1.0, 10));
        dishs1.add(new ModelDish("è‚ ç²‰", 1.0, 10));
        dishs1.add(new ModelDish("ç»¿èŒ¶é¥¼", 1.0, 10));
        dishs1.add(new ModelDish("èŠ±å?·", 1.0, 10));
        dishs1.add(new ModelDish("åŒ…å­?", 1.0, 10));
        ModelDishMenu breakfast = new ModelDishMenu("æ—©ç‚¹", dishs1);

        ArrayList<ModelDish> dishs2 = new ArrayList<>();
        dishs2.add(new ModelDish("ç²¥", 1.0, 10));
        dishs2.add(new ModelDish("ç‚’é¥­", 1.0, 10));
        dishs2.add(new ModelDish("ç‚’ç±³ç²‰", 1.0, 10));
        dishs2.add(new ModelDish("ç‚’ç²¿æ?¡", 1.0, 10));
        dishs2.add(new ModelDish("ç‚’ç‰›æ²³", 1.0, 10));
        dishs2.add(new ModelDish("ç‚’è?œ", 1.0, 10));
        ModelDishMenu launch = new ModelDishMenu("å?ˆé¤?", dishs2);

        ArrayList<ModelDish> dishs3 = new ArrayList<>();
        dishs3.add(new ModelDish("æ·‹è?œ", 1.0, 10));
        dishs3.add(new ModelDish("å·?è?œ", 1.0, 10));
        dishs3.add(new ModelDish("æ¹˜è?œ", 1.0, 10));
        dishs3.add(new ModelDish("ç²¤è?œ", 1.0, 10));
        dishs3.add(new ModelDish("èµ£è?œ", 1.0, 10));
        dishs3.add(new ModelDish("ä¸œåŒ—è?œ", 1.0, 10));
        ModelDishMenu evening = new ModelDishMenu("æ™šé¤?", dishs3);

        ArrayList<ModelDish> dishs4 = new ArrayList<>();
        dishs4.add(new ModelDish("æ·‹è?œ", 1.0, 10));
        dishs4.add(new ModelDish("å·?è?œ", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ1", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ2", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ3", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ4", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ5", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ6", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ7", 1.0, 10));
        dishs4.add(new ModelDish("æ¹˜è?œ8", 1.0, 10));
        dishs4.add(new ModelDish("ç²¤è?œ", 1.0, 10));
        dishs4.add(new ModelDish("èµ£è?œ", 1.0, 10));
        dishs4.add(new ModelDish("ä¸œåŒ—è?œ", 1.0, 10));
        ModelDishMenu menu1 = new ModelDishMenu("å¤œå®µ", dishs4);

        mModelDishMenuList.add(breakfast);
        mModelDishMenuList.add(launch);
        mModelDishMenuList.add(evening);
        mModelDishMenuList.add(menu1);
    }

    private void initAdapter() {
        leftAdapter = new AdapterLeftMenu(this, mModelDishMenuList);
        rightAdapter = new AdapterRightDish(this, mModelDishMenuList, mModelShopCart);
        mRightMenu.setAdapter(rightAdapter);
        mLeftMenu.setAdapter(leftAdapter);
        leftAdapter.addItemSelectedListener(this);
        rightAdapter.setShopCartInterface(this);
        initHeadView();
    }

    private void initHeadView() {
        headMenu = rightAdapter.getMenuOfMenuByPosition(0);
        headerLayout.setContentDescription("0");
        headerView.setText(headMenu.getMenuName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        leftAdapter.removeItemSelectedListener(this);
    }

    private void showHeadView() {
        headerLayout.setTranslationY(0);
        View underView = mRightMenu.findChildViewUnder(headerView.getX(), 0);
        if (underView != null && underView.getContentDescription() != null) {
            int position = Integer.parseInt(underView.getContentDescription().toString());
            ModelDishMenu menu = rightAdapter.getMenuOfMenuByPosition(position + 1);
            headMenu = menu;
            headerView.setText(headMenu.getMenuName());
            for (int i = 0; i < mModelDishMenuList.size(); i++) {
                if (mModelDishMenuList.get(i) == headMenu) {
                    leftAdapter.setSelectedNum(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onLeftItemSelected(int position, ModelDishMenu menu) {
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += mModelDishMenuList.get(i).getModelDishList().size() + 1;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRightMenu.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(sum, 0);
        leftClickType = true;
    }

    @Override
    public void add(View view, int position) {
        int[] addLocation = new int[2];
        int[] cartLocation = new int[2];
        int[] recycleLocation = new int[2];
        view.getLocationInWindow(addLocation);
        mShoppingCart.getLocationInWindow(cartLocation);
        mRightMenu.getLocationInWindow(recycleLocation);

        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();

        startP.x = addLocation[0];
        startP.y = addLocation[1] - recycleLocation[1];
        endP.x = cartLocation[0];
        endP.y = cartLocation[1] - recycleLocation[1];
        controlP.x = endP.x;
        controlP.y = startP.y;

        final RxFakeAddImageView rxFakeAddImageView = new RxFakeAddImageView(this);
        mMainLayout.addView(rxFakeAddImageView);
        rxFakeAddImageView.setImageResource(R.drawable.ic_add_circle_blue_700_36dp);
        rxFakeAddImageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        rxFakeAddImageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        rxFakeAddImageView.setVisibility(View.VISIBLE);
        ObjectAnimator addAnimator = ObjectAnimator.ofObject(rxFakeAddImageView, "mPointF",
                new RxPointFTypeEvaluator(controlP), startP, endP);
        addAnimator.setInterpolator(new AccelerateInterpolator());
        addAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                rxFakeAddImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                rxFakeAddImageView.setVisibility(View.GONE);
                mMainLayout.removeView(rxFakeAddImageView);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        ObjectAnimator scaleAnimatorX = new ObjectAnimator().ofFloat(mShoppingCart, "scaleX", 0.6f, 1.0f);
        ObjectAnimator scaleAnimatorY = new ObjectAnimator().ofFloat(mShoppingCart, "scaleY", 0.6f, 1.0f);
        scaleAnimatorX.setInterpolator(new AccelerateInterpolator());
        scaleAnimatorY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleAnimatorX).with(scaleAnimatorY).after(addAnimator);
        animatorSet.setDuration(800);
        animatorSet.start();

        showTotalPrice();
    }

    @Override
    public void remove(View view, int position) {
        showTotalPrice();
    }

    private void showTotalPrice() {
        if (mModelShopCart != null && mModelShopCart.getShoppingTotalPrice() > 0) {
            totalPriceTextView.setVisibility(View.VISIBLE);
            totalPriceTextView.setText("Â¥ " + mModelShopCart.getShoppingTotalPrice());
            totalPriceNumTextView.setVisibility(View.VISIBLE);
            totalPriceNumTextView.setText("" + mModelShopCart.getShoppingAccount());
        } else {
            totalPriceTextView.setVisibility(View.GONE);
            totalPriceNumTextView.setVisibility(View.GONE);
        }
    }

    private void showCart(View view) {
        if (mModelShopCart != null && mModelShopCart.getShoppingAccount() > 0) {
            RxDialogShopCart dialog = new RxDialogShopCart(this, mModelShopCart, R.style.cartdialog);
            Window window = dialog.getWindow();
            dialog.setShopCartDialogImp(this);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.show();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            params.dimAmount = 0.5f;
            window.setAttributes(params);
        }
    }

    @Override
    public void dialogDismiss() {
        showTotalPrice();
        rightAdapter.notifyDataSetChanged();
    }
}
