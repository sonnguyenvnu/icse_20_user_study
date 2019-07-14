package com.vondear.rxdemo.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.vondear.rxdemo.R;
import com.vondear.rxtool.RxBarTool;
import com.vondear.rxtool.RxPhotoTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxui.activity.ActivityBase;
import com.vondear.rxui.view.RxTitle;
import com.vondear.rxui.view.dialog.RxDialogChooseImage;
import com.vondear.rxui.view.dialog.RxDialogScaleView;
import com.vondear.rxui.view.dialog.RxDialogSureCancel;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vondear.rxui.view.dialog.RxDialogChooseImage.LayoutType.TITLE;


/**
 * @author vondear
 */
public class ActivityRxPhoto extends ActivityBase {


    @BindView(R.id.rx_title)
    RxTitle mRxTitle;
    @BindView(R.id.tv_bg)
    TextView mTvBg;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.ll_anchor_left)
    LinearLayout mLlAnchorLeft;
    @BindView(R.id.rl_avatar)
    RelativeLayout mRlAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_constellation)
    TextView mTvConstellation;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_lables)
    TextView mTvLables;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.editText2)
    TextView mEditText2;
    @BindView(R.id.btn_exit)
    Button mBtnExit;
    @BindView(R.id.activity_user)
    LinearLayout mActivityUser;
    private Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBarTool.noTitle(this);
        setContentView(R.layout.activity_von_photo);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        Resources r = mContext.getResources();
        resultUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(R.drawable.circle_elves_ball) + "/"
                + r.getResourceTypeName(R.drawable.circle_elves_ball) + "/"
                + r.getResourceEntryName(R.drawable.circle_elves_ball));

        mRxTitle.setLeftFinish(mContext);

        mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialogChooseImage();
            }
        });
        mIvAvatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                RxImageTool.showBigImageView(mContext, resultUri);
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mContext);
                rxDialogScaleView.setImage(resultUri);
                rxDialogScaleView.show();
                return false;
            }
        });
    }

    private void initDialogChooseImage() {
        RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(mContext, TITLE);
        dialogChooseImage.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RxPhotoTool.GET_IMAGE_FROM_PHONE://选择相册之�?�的处�?�
                if (resultCode == RESULT_OK) {
//                    RxPhotoTool.cropImage(ActivityUser.this, );// �?剪图片
                    initUCrop(data.getData());
                }

                break;
            case RxPhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之�?�的处�?�
                if (resultCode == RESULT_OK) {
                    /* data.getExtras().get("data");*/
//                    RxPhotoTool.cropImage(ActivityUser.this, RxPhotoTool.imageUriFromCamera);// �?剪图片
                    initUCrop(RxPhotoTool.imageUriFromCamera);
                }

                break;
            case RxPhotoTool.CROP_IMAGE://普通�?剪�?�的处�?�
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.circle_elves_ball)
                        //异常�?��?图(当加载异常的时候出现的图片)
                        .error(R.drawable.circle_elves_ball)
                        //�?止Glide硬盘缓存缓存
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

                Glide.with(mContext).
                        load(RxPhotoTool.cropImageUri).
                        apply(options).
                        thumbnail(0.5f).
                        into(mIvAvatar);
//                RequestUpdateAvatar(new File(RxPhotoTool.getRealFilePath(mContext, RxPhotoTool.cropImageUri)));
                break;

            case UCrop.REQUEST_CROP://UCrop�?剪之�?�的处�?�
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    roadImageView(resultUri, mIvAvatar);
                    RxSPTool.putContent(mContext, "AVATAR", resultUri.toString());
                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
                break;
            case UCrop.RESULT_ERROR://UCrop�?剪错误之�?�的处�?�
                final Throwable cropError = UCrop.getError(data);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //从Uri中加载图片 并将其转化�?File文件返回
    private File roadImageView(Uri uri, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.circle_elves_ball)
                //异常�?��?图(当加载异常的时候出现的图片)
                .error(R.drawable.circle_elves_ball)
                .transform(new CircleCrop())
                //�?止Glide硬盘缓存缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(mContext).
                load(uri).
                apply(options).
                thumbnail(0.5f).
                into(imageView);

        return (new File(RxPhotoTool.getImageAbsolutePath(this, uri)));
    }

    private void initUCrop(Uri uri) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), imageName + ".jpeg"));

        UCrop.Options options = new UCrop.Options();
        //设置�?剪图片�?��?作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置�?�?底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状�?�?颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5);
        //设置图片在切�?�比例时的动画
        options.setImageToCropBoundsAnimDuration(666);
        //设置�?剪窗�?�是�?�为椭圆
        //options.setCircleDimmedLayer(true);
        //设置是�?�展示矩形�?剪框
        // options.setShowCropFrame(false);
        //设置�?剪框横竖线的宽度
        //options.setCropGridStrokeWidth(20);
        //设置�?剪框横竖线的颜色
        //options.setCropGridColor(Color.GREEN);
        //设置竖线的数�?
        //options.setCropGridColumnCount(2);
        //设置横线的数�?
        //options.setCropGridRowCount(1);

        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(this);
    }

    @OnClick(R.id.btn_exit)
    public void onClick() {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(this);
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rxDialogSureCancel.show();
    }
}
