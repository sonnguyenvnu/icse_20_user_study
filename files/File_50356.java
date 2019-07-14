package cn.finalteam.rxgalleryfinal.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.yalantis.ucrop.model.AspectRatio;

import java.util.List;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.sample.imageloader.ImageLoaderActivity;
import cn.finalteam.rxgalleryfinal.ui.RxGalleryListener;
import cn.finalteam.rxgalleryfinal.ui.activity.MediaActivity;
import cn.finalteam.rxgalleryfinal.ui.base.IMultiImageCheckedListener;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;
import cn.finalteam.rxgalleryfinal.utils.Logger;
import cn.finalteam.rxgalleryfinal.utils.PermissionCheckUtils;

/**
 * 示例
 *
 * @author KARL-dujinyang
 *         <p>
 *         openGallery 返回 void,如果想使用RxGalleryFinal对象，请在 openGallery() 之�?返回 RxGalleryFinal 对象
 *         <p>
 *         <p>
 *         RxGalleryFinal radio = RxGalleryFinal
 *         with(MainActivity.this)
 *         image()
 *         radio();
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton mRbRadioIMG, mRbMutiIMG, mRbOpenC, mRbRadioVD, mRbMutiVD, mRbCropZD, mRbCropZVD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_image_loader).setOnClickListener(this);
        findViewById(R.id.btn_open_def_radio).setOnClickListener(this);
        findViewById(R.id.btn_open_def_multi).setOnClickListener(this);
        findViewById(R.id.btn_open_img).setOnClickListener(this);
        findViewById(R.id.btn_open_vd).setOnClickListener(this);
        findViewById(R.id.btn_open_crop).setOnClickListener(this);
        findViewById(R.id.btn_open_set_path).setOnClickListener(this);
        mRbRadioIMG = (RadioButton) findViewById(R.id.rb_radio_img);
        mRbMutiIMG = (RadioButton) findViewById(R.id.rb_muti_img);
        mRbRadioVD = (RadioButton) findViewById(R.id.rb_radio_vd);
        mRbMutiVD = (RadioButton) findViewById(R.id.rb_muti_vd);
        mRbOpenC = (RadioButton) findViewById(R.id.rb_openC);
        mRbCropZD = (RadioButton) findViewById(R.id.rb_radio_crop_z);
        mRbCropZVD = (RadioButton) findViewById(R.id.rb_radio_crop_vz);
        //多选事件的回调
        RxGalleryListener
                .getInstance()
                .setMultiImageCheckedListener(
                        new IMultiImageCheckedListener() {
                            @Override
                            public void selectedImg(Object t, boolean isChecked) {
                                Toast.makeText(getBaseContext(), isChecked ? "选中" : "�?�消选中", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void selectedImgMax(Object t, boolean isChecked, int maxSize) {
                                Toast.makeText(getBaseContext(), "你最多�?�能选择" + maxSize + "张图片", Toast.LENGTH_SHORT).show();
                            }
                        });
        //�?剪图片的回调
        RxGalleryListener
                .getInstance()
                .setRadioImageCheckedListener(
                        new IRadioImageCheckedListener() {
                            @Override
                            public void cropAfter(Object t) {
                                Toast.makeText(getBaseContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public boolean isActivityFinish() {
                                return false;
                            }
                        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_image_loader:
                Intent intent = new Intent(v.getContext(), ImageLoaderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.btn_open_def_radio:
                openRadio();
                break;
            case R.id.btn_open_def_multi:
                openMulti();
                break;
            case R.id.btn_open_img:
                openImageSelect();
                break;
            case R.id.btn_open_vd:
                openVideoSelect();
                break;
            case R.id.btn_open_crop:
                openCrop();
                break;
            case R.id.btn_open_set_path:
                setPath();
                break;
        }

    }

    /**
     * 设置 照片路径 和 �?剪路径
     */
    private void setPath() {
        RxGalleryFinalApi.setImgSaveRxSDCard("dujinyang");
        RxGalleryFinalApi.setImgSaveRxCropSDCard("dujinyang/crop");//�?剪会自动生�?路径；也�?�以手动设置�?剪的路径；
    }

    /**
     * 直接�?剪  or  �?照并�?剪( 查看 onActivityResult())
     */
    private void openCrop() {
        if (mRbCropZD.isChecked()) {
            //直接�?剪
            String inputImg = "";
            Toast.makeText(MainActivity.this, "没有图片演示，请选择‘�?照�?剪’功能", Toast.LENGTH_SHORT).show();
            //  RxGalleryFinalApi.cropScannerForResult(MainActivity.this, RxGalleryFinalApi.getModelPath(), inputImg);//调用�?剪.RxGalleryFinalApi.getModelPath()为模拟的输出路径
        } else {
            //            RxGalleryFinalApi.openZKCamera(MainActivity.this);

            SimpleRxGalleryFinal.get().init(
                    new SimpleRxGalleryFinal.RxGalleryFinalCropListener() {
                        @NonNull
                        @Override
                        public Activity getSimpleActivity() {
                            return MainActivity.this;
                        }

                        @Override
                        public void onCropCancel() {
                            Toast.makeText(getSimpleActivity(), "�?剪被�?�消", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCropSuccess(@Nullable Uri uri) {
                            Toast.makeText(getSimpleActivity(), "�?剪�?功：" + uri, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCropError(@NonNull String errorMessage) {
                            Toast.makeText(getSimpleActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
            ).openCamera();
        }
    }

    /**
     * 视频
     * �?�选 多选
     */
    private void openVideoSelect() {
        if (mRbRadioVD.isChecked()) {
            openVideoSelectRadioMethod();
        } else if (mRbMutiVD.isChecked()) {
            openVideoSelectMultiMethod(0);
        }
    }

    /**
     * 图片
     * �?�选，多选，  直接打开相机
     */
    private void openImageSelect() {
        if (mRbRadioIMG.isChecked()) {
            openImageSelectRadioMethod(3);
        } else if (mRbMutiIMG.isChecked()) {
            openImageSelectMultiMethod(1);
        } else {
            if (PermissionCheckUtils.checkCameraPermission(this, "", MediaActivity.REQUEST_CAMERA_ACCESS_PERMISSION)) {
                RxGalleryFinalApi.openZKCamera(MainActivity.this);
            }
        }
    }

    private List<MediaBean> list = null;

    /**
     * 自定义多选
     */
    private void openMulti() {
//        RxGalleryFinal.with(this).hidePreview();
        RxGalleryFinal rxGalleryFinal = RxGalleryFinal
                .with(MainActivity.this)
                .image()
                .multiple();
        if (list != null && !list.isEmpty()) {
            rxGalleryFinal
                    .selected(list);
        }
        rxGalleryFinal.maxSize(8)
                .imageLoader(ImageLoaderType.FRESCO)
                .subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>() {

                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                        list = imageMultipleResultEvent.getResult();
                        Toast.makeText(getBaseContext(), "已选择" + imageMultipleResultEvent.getResult().size() + "张图片", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        Toast.makeText(getBaseContext(), "OVER", Toast.LENGTH_SHORT).show();
                    }
                })
                .openGallery();
    }

    /**
     * 自定义�?�选
     */
    private void openRadio() {
        RxGalleryFinal
                .with(MainActivity.this)
                .image()
                .radio()
                .cropAspectRatioOptions(0, new AspectRatio("3:3", 30, 10))
                .crop()
                .imageLoader(ImageLoaderType.FRESCO)
                .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        Toast.makeText(getBaseContext(), "选中了图片路径：" + imageRadioResultEvent.getResult().getOriginalPath(), Toast.LENGTH_SHORT).show();
                    }
                })
                .openGallery();
    }

    /**
     * 视频多选回调
     */
    private void openVideoSelectMultiMethod(int type) {
        switch (type) {
            case 0:

                //使用默认的�?�数
                RxGalleryFinalApi
                        .getInstance(this)
                        .setVDMultipleResultEvent(
                                new RxBusResultDisposable<ImageMultipleResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                        Logger.i("多选视频的回调");
                                    }
                                }).open();

                break;
            case 1:

                //使用自定义的�?�数
                RxGalleryFinalApi
                        .getInstance(this)
                        .setType(RxGalleryFinalApi.SelectRXType.TYPE_VIDEO, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_MULTI)
                        .setVDMultipleResultEvent(
                                new RxBusResultDisposable<ImageMultipleResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                        Logger.i("多选视频的回调");
                                    }
                                }).open();

                break;
            case 2:

                //直接打开
                RxGalleryFinalApi
                        .openMultiSelectVD(this, new RxBusResultDisposable<ImageMultipleResultEvent>() {
                            @Override
                            protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                Logger.i("多选视频的回调");
                            }
                        });

                break;
        }
    }

    /**
     * 视频�?�选回调
     */
    private void openVideoSelectRadioMethod() {
        RxGalleryFinalApi
                .getInstance(MainActivity.this)
                .setType(RxGalleryFinalApi.SelectRXType.TYPE_VIDEO, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_RADIO)
                .setVDRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        Toast.makeText(getApplicationContext(), imageRadioResultEvent.getResult().getOriginalPath(), Toast.LENGTH_SHORT).show();
                    }
                })
                .open();
    }

    /**
     * OPEN 图片多选实现方法
     * <p>
     * 默认使用 第三个 ，如果�?行sample,�?�自行改�?�Type，�?行Demo查看效果
     */
    private void openImageSelectMultiMethod(int type) {
        switch (type) {
            case 0:

                //使用默认的�?�数
                RxGalleryFinalApi
                        .getInstance(MainActivity.this)
                        .setImageMultipleResultEvent(
                                new RxBusResultDisposable<ImageMultipleResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                        Logger.i("多选图片的回调");
                                    }
                                }).open();

                break;
            case 1:

                //使用自定义的�?�数
                RxGalleryFinalApi
                        .getInstance(MainActivity.this)
                        .setType(RxGalleryFinalApi.SelectRXType.TYPE_IMAGE, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_MULTI)
                        .setImageMultipleResultEvent(new RxBusResultDisposable<ImageMultipleResultEvent>() {
                            @Override
                            protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                Logger.i("多选图片的回调");
                            }
                        }).open();

                break;
            case 2:

                //直接打开
                RxGalleryFinalApi.openMultiSelectImage(this, new RxBusResultDisposable<ImageMultipleResultEvent>() {
                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                        Logger.i("多选图片的回调");
                    }
                });

                break;
        }

    }

    /**
     * OPEN 图片�?�选实现方法
     * <p>
     * 默认使用 第三个 ，如果�?行sample,�?�自行改�?�Type，�?行Demo查看效果
     */
    private void openImageSelectRadioMethod(int type) {
        RxGalleryFinalApi instance = RxGalleryFinalApi.getInstance(MainActivity.this);
        switch (type) {
            case 0:

                //打开�?�选图片，默认�?�数
                instance
                        .setImageRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                Logger.i("�?�选图片的回调");
                            }
                        }).open();

                break;
            case 1:

                //设置自定义的�?�数
                instance
                        .setType(RxGalleryFinalApi.SelectRXType.TYPE_IMAGE, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_RADIO)
                        .setImageRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                Logger.i("�?�选图片的回调");
                            }
                        }).open();

                break;
            case 2:

                //快速打开�?�选图片,flag使用true�?�?剪
                RxGalleryFinalApi
                        .openRadioSelectImage(MainActivity.this, new RxBusResultDisposable<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent o) throws Exception {
                                Logger.i("�?�选图片的回调");
                            }
                        }, true);

                break;
            case 3:

                //�?�选，使用RxGalleryFinal默认设置，并且带有�?剪
                instance
                        .openGalleryRadioImgDefault(
                                new RxBusResultDisposable<ImageRadioResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                        Logger.i("�?��?选择图片就会触�?�");
                                    }
                                })
                        .onCropImageResult(
                                new IRadioImageCheckedListener() {
                                    @Override
                                    public void cropAfter(Object t) {
                                        Logger.i("�?剪完�?");
                                    }

                                    @Override
                                    public boolean isActivityFinish() {
                                        Logger.i("返回false�?关闭，返回true则为关闭");
                                        return true;
                                    }
                                });

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SimpleRxGalleryFinal.get().onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RxGalleryFinalApi.TAKE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            Logger.i("�?照OK，图片路径:" + RxGalleryFinalApi.fileImagePath.getPath());
//            //刷新相册数�?�库
//            RxGalleryFinalApi.openZKCameraForResult(MainActivity.this, new MediaScanner.ScanCallback() {
//                @Override
//                public void onScanCompleted(String[] strings) {
//                    Logger.i(String.format("�?照�?功,图片存储路径:%s", strings[0]));
//                    Logger.d("演示�?照�?�进行图片�?剪，根�?�实际开�?�需求�?�去掉上�?�的判断");
//                    RxGalleryFinalApi.cropScannerForResult(MainActivity.this, RxGalleryFinalApi.getModelPath(), strings[0]);//调用�?剪.RxGalleryFinalApi.getModelPath()为默认的输出路径
//                }
//            });
//        } else {
//            Logger.i("失敗");
//        }
    }
}
