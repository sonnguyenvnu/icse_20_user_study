package com.vondear.rxfeature.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.vondear.rxfeature.R;
import com.vondear.rxfeature.module.scaner.CameraManager;
import com.vondear.rxfeature.module.scaner.OnRxScanerListener;
import com.vondear.rxfeature.module.scaner.PlanarYUVLuminanceSource;
import com.vondear.rxfeature.module.scaner.decoding.InactivityTimer;
import com.vondear.rxfeature.tool.RxQrBarTool;
import com.vondear.rxtool.RxAnimationTool;
import com.vondear.rxtool.RxBarTool;
import com.vondear.rxtool.RxBeepTool;
import com.vondear.rxtool.RxConstants;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxPhotoTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.view.RxToast;
import com.vondear.rxui.activity.ActivityBase;
import com.vondear.rxui.view.dialog.RxDialogSure;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static android.content.ContentValues.TAG;

/**
 * @author vondear
 */
public class ActivityScanerCode extends ActivityBase {

    /**
     * æ‰«æ??ç»“æžœç›‘å?¬
     */
    private static OnRxScanerListener mScanerListener;

    private InactivityTimer inactivityTimer;

    /**
     * æ‰«æ??å¤„ç?†
     */
    private CaptureActivityHandler handler;

    /**
     * æ•´ä½“æ ¹å¸ƒå±€
     */
    private RelativeLayout mContainer = null;

    /**
     * æ‰«æ??æ¡†æ ¹å¸ƒå±€
     */
    private RelativeLayout mCropLayout = null;

    /**
     * æ‰«æ??è¾¹ç•Œçš„å®½åº¦
     */
    private int mCropWidth = 0;

    /**
     * æ‰«æ??è¾¹ç•Œçš„é«˜åº¦
     */
    private int mCropHeight = 0;

    /**
     * æ˜¯å?¦æœ‰é¢„è§ˆ
     */
    private boolean hasSurface;

    /**
     * æ‰«æ??æˆ?åŠŸå?Žæ˜¯å?¦éœ‡åŠ¨
     */
    private boolean vibrate = true;

    /**
     * é—ªå…‰ç?¯å¼€å?¯çŠ¶æ€?
     */
    private boolean mFlashing = true;

    /**
     * ç”Ÿæˆ?äºŒç»´ç ? & æ?¡å½¢ç ? å¸ƒå±€
     */
    private LinearLayout mLlScanHelp;

    /**
     * é—ªå…‰ç?¯ æŒ‰é’®
     */
    private ImageView mIvLight;

    /**
     * æ‰«æ??ç»“æžœæ˜¾ç¤ºæ¡†
     */
    private RxDialogSure rxDialogSure;

    /**
     * è®¾ç½®æ‰«æ??ä¿¡æ?¯å›žè°ƒ
     */
    public static void setScanerListener(OnRxScanerListener scanerListener) {
        mScanerListener = scanerListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBarTool.setNoTitle(this);
        setContentView(R.layout.activity_scaner_code);
        RxBarTool.setTransparentStatusBar(this);
        //ç•Œé?¢æŽ§ä»¶åˆ?å§‹åŒ–
        initDecode();
        initView();
        //æ?ƒé™?åˆ?å§‹åŒ–
        initPermission();
        //æ‰«æ??åŠ¨ç”»åˆ?å§‹åŒ–
        initScanerAnimation();
        //åˆ?å§‹åŒ– CameraManager
        CameraManager.init(mContext);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }

    private void initDecode() {
        multiFormatReader = new MultiFormatReader();

        // è§£ç ?çš„å?‚æ•°
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>(2);
        // å?¯ä»¥è§£æž?çš„ç¼–ç ?ç±»åž‹
        Vector<BarcodeFormat> decodeFormats = new Vector<BarcodeFormat>();
        if (decodeFormats == null || decodeFormats.isEmpty()) {
            decodeFormats = new Vector<BarcodeFormat>();

            Vector<BarcodeFormat> PRODUCT_FORMATS = new Vector<BarcodeFormat>(5);
            PRODUCT_FORMATS.add(BarcodeFormat.UPC_A);
            PRODUCT_FORMATS.add(BarcodeFormat.UPC_E);
            PRODUCT_FORMATS.add(BarcodeFormat.EAN_13);
            PRODUCT_FORMATS.add(BarcodeFormat.EAN_8);
            // PRODUCT_FORMATS.add(BarcodeFormat.RSS14);
            Vector<BarcodeFormat> ONE_D_FORMATS = new Vector<BarcodeFormat>(PRODUCT_FORMATS.size() + 4);
            ONE_D_FORMATS.addAll(PRODUCT_FORMATS);
            ONE_D_FORMATS.add(BarcodeFormat.CODE_39);
            ONE_D_FORMATS.add(BarcodeFormat.CODE_93);
            ONE_D_FORMATS.add(BarcodeFormat.CODE_128);
            ONE_D_FORMATS.add(BarcodeFormat.ITF);
            Vector<BarcodeFormat> QR_CODE_FORMATS = new Vector<BarcodeFormat>(1);
            QR_CODE_FORMATS.add(BarcodeFormat.QR_CODE);
            Vector<BarcodeFormat> DATA_MATRIX_FORMATS = new Vector<BarcodeFormat>(1);
            DATA_MATRIX_FORMATS.add(BarcodeFormat.DATA_MATRIX);

            // è¿™é‡Œè®¾ç½®å?¯æ‰«æ??çš„ç±»åž‹ï¼Œæˆ‘è¿™é‡Œé€‰æ‹©äº†éƒ½æ”¯æŒ?
            decodeFormats.addAll(ONE_D_FORMATS);
            decodeFormats.addAll(QR_CODE_FORMATS);
            decodeFormats.addAll(DATA_MATRIX_FORMATS);
        }
        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);

        multiFormatReader.setHints(hints);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = findViewById(R.id.capture_preview);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            //Cameraåˆ?å§‹åŒ–
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    if (!hasSurface) {
                        hasSurface = true;
                        initCamera(holder);
                    }
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    hasSurface = false;

                }
            });
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        mScanerListener = null;
        super.onDestroy();
    }

    private void initView() {
        mIvLight = findViewById(R.id.top_mask);
        mContainer = findViewById(R.id.capture_containter);
        mCropLayout = findViewById(R.id.capture_crop_layout);
        mLlScanHelp = findViewById(R.id.ll_scan_help);


    }

    private void initPermission() {
        //è¯·æ±‚Cameraæ?ƒé™? ä¸Ž æ–‡ä»¶è¯»å†™ æ?ƒé™?
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private void initScanerAnimation() {
        ImageView mQrLineView = findViewById(R.id.capture_scan_line);
        RxAnimationTool.ScaleUpDowm(mQrLineView);
    }

    public int getCropWidth() {
        return mCropWidth;
    }

    public void setCropWidth(int cropWidth) {
        mCropWidth = cropWidth;
        CameraManager.FRAME_WIDTH = mCropWidth;

    }

    public int getCropHeight() {
        return mCropHeight;
    }

    public void setCropHeight(int cropHeight) {
        this.mCropHeight = cropHeight;
        CameraManager.FRAME_HEIGHT = mCropHeight;
    }

    public void btn(View view) {
        int viewId = view.getId();
        if (viewId == R.id.top_mask) {
            light();
        } else if (viewId == R.id.top_back) {
            finish();
        } else if (viewId == R.id.top_openpicture) {
            RxPhotoTool.openLocalImage(mContext);
        }
    }

    private void light() {
        if (mFlashing) {
            mFlashing = false;
            // å¼€é—ªå…‰ç?¯
            CameraManager.get().openLight();
        } else {
            mFlashing = true;
            // å…³é—ªå…‰ç?¯
            CameraManager.get().offLight();
        }

    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            Point point = CameraManager.get().getCameraResolution();
            AtomicInteger width = new AtomicInteger(point.y);
            AtomicInteger height = new AtomicInteger(point.x);
            int cropWidth = mCropLayout.getWidth() * width.get() / mContainer.getWidth();
            int cropHeight = mCropLayout.getHeight() * height.get() / mContainer.getHeight();
            setCropWidth(cropWidth);
            setCropHeight(cropHeight);
        } catch (IOException | RuntimeException ioe) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler();
        }
    }


    //--------------------------------------æ‰“å¼€æœ¬åœ°å›¾ç‰‡è¯†åˆ«äºŒç»´ç ? start---------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ContentResolver resolver = getContentResolver();
            // ç…§ç‰‡çš„åŽŸå§‹èµ„æº?åœ°å?€
            Uri originalUri = data.getData();
            try {
                // ä½¿ç”¨ContentProvideré€šè¿‡URIèŽ·å?–åŽŸå§‹å›¾ç‰‡
                Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);

                // å¼€å§‹å¯¹å›¾åƒ?èµ„æº?è§£ç ?
                Result rawResult = RxQrBarTool.decodeFromPhoto(photo);
                if (rawResult != null) {
                    if (mScanerListener == null) {
                        initDialogResult(rawResult);
                    } else {
                        mScanerListener.onSuccess("From to Picture", rawResult);
                    }
                } else {
                    if (mScanerListener == null) {
                        RxToast.error("å›¾ç‰‡è¯†åˆ«å¤±è´¥.");
                    } else {
                        mScanerListener.onFail("From to Picture", "å›¾ç‰‡è¯†åˆ«å¤±è´¥");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //========================================æ‰“å¼€æœ¬åœ°å›¾ç‰‡è¯†åˆ«äºŒç»´ç ? end=================================


    private void initDialogResult(Result result) {
        BarcodeFormat type = result.getBarcodeFormat();
        String realContent = result.getText();

        if (rxDialogSure == null) {
            //æ??ç¤ºå¼¹çª—
            rxDialogSure = new RxDialogSure(mContext);
        }

        if (BarcodeFormat.QR_CODE.equals(type)) {
            rxDialogSure.setTitle("äºŒç»´ç ?æ‰«æ??ç»“æžœ");
        } else if (BarcodeFormat.EAN_13.equals(type)) {
            rxDialogSure.setTitle("æ?¡å½¢ç ?æ‰«æ??ç»“æžœ");
        } else {
            rxDialogSure.setTitle("æ‰«æ??ç»“æžœ");
        }

        rxDialogSure.setContent(realContent);
        rxDialogSure.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSure.cancel();
            }
        });
        rxDialogSure.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (handler != null) {
                    // è¿žç»­æ‰«æ??ï¼Œä¸?å?‘é€?æ­¤æ¶ˆæ?¯æ‰«æ??ä¸€æ¬¡ç»“æ?Ÿå?Žå°±ä¸?èƒ½å†?æ¬¡æ‰«æ??
                    handler.sendEmptyMessage(R.id.restart_preview);
                }
            }
        });

        if (!rxDialogSure.isShowing()) {
            rxDialogSure.show();
        }

        RxSPTool.putContent(mContext, RxConstants.SP_SCAN_CODE, RxDataTool.stringToInt(RxSPTool.getContent(mContext, RxConstants.SP_SCAN_CODE)) + 1 + "");
    }

    public void handleDecode(Result result) {
        inactivityTimer.onActivity();
        //æ‰«æ??æˆ?åŠŸä¹‹å?Žçš„æŒ¯åŠ¨ä¸Žå£°éŸ³æ??ç¤º
        RxBeepTool.playBeep(mContext, vibrate);

        String result1 = result.getText();
        Log.v("äºŒç»´ç ?/æ?¡å½¢ç ? æ‰«æ??ç»“æžœ", result1);
        if (mScanerListener == null) {
            RxToast.success(result1);
            initDialogResult(result);
        } else {
            mScanerListener.onSuccess("From to Camera", result);
        }
    }
    //==============================================================================================è§£æž?ç»“æžœ å?Š å?Žç»­å¤„ç?† end

    final class CaptureActivityHandler extends Handler {

        DecodeThread decodeThread = null;
        private State state;

        public CaptureActivityHandler() {
            decodeThread = new DecodeThread();
            decodeThread.start();
            state = State.SUCCESS;
            CameraManager.get().startPreview();
            restartPreviewAndDecode();
        }

        @Override
        public void handleMessage(Message message) {
            if (message.what == R.id.auto_focus) {
                if (state == State.PREVIEW) {
                    CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
                }
            } else if (message.what == R.id.restart_preview) {
                restartPreviewAndDecode();
            } else if (message.what == R.id.decode_succeeded) {
                state = State.SUCCESS;
                handleDecode((Result) message.obj);// è§£æž?æˆ?åŠŸï¼Œå›žè°ƒ
            } else if (message.what == R.id.decode_failed) {
                state = State.PREVIEW;
                CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
            }
        }

        public void quitSynchronously() {
            state = State.DONE;
            decodeThread.interrupt();
            CameraManager.get().stopPreview();
            removeMessages(R.id.decode_succeeded);
            removeMessages(R.id.decode_failed);
            removeMessages(R.id.decode);
            removeMessages(R.id.auto_focus);
        }

        private void restartPreviewAndDecode() {
            if (state == State.SUCCESS) {
                state = State.PREVIEW;
                CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
                CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
            }
        }
    }

    final class DecodeThread extends Thread {

        private final CountDownLatch handlerInitLatch;
        private Handler handler;

        DecodeThread() {
            handlerInitLatch = new CountDownLatch(1);
        }

        Handler getHandler() {
            try {
                handlerInitLatch.await();
            } catch (InterruptedException ie) {
                // continue?
            }
            return handler;
        }

        @Override
        public void run() {
            Looper.prepare();
            handler = new DecodeHandler();
            handlerInitLatch.countDown();
            Looper.loop();
        }
    }

    final class DecodeHandler extends Handler {
        DecodeHandler() {
        }

        @Override
        public void handleMessage(Message message) {
            if (message.what == R.id.decode) {
                decode((byte[]) message.obj, message.arg1, message.arg2);
            } else if (message.what == R.id.quit) {
                Looper.myLooper().quit();
            }
        }
    }

    private MultiFormatReader multiFormatReader;

    private void decode(byte[] data, int width, int height) {
        long start = System.currentTimeMillis();
        Result rawResult = null;

        //modify here
        byte[] rotatedData = new byte[data.length];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rotatedData[x * height + height - y - 1] = data[x + y * width];
            }
        }
        // Here we are swapping, that's the difference to #11
        int tmp = width;
        width = height;
        height = tmp;

        PlanarYUVLuminanceSource source = CameraManager.get().buildLuminanceSource(rotatedData, width, height);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try {
            rawResult = multiFormatReader.decodeWithState(bitmap);
        } catch (ReaderException e) {
            // continue
        } finally {
            multiFormatReader.reset();
        }

        if (rawResult != null) {
            long end = System.currentTimeMillis();
            Log.d(TAG, "Found barcode (" + (end - start) + " ms):\n" + rawResult.toString());
            Message message = Message.obtain(handler, R.id.decode_succeeded, rawResult);
            Bundle bundle = new Bundle();
            bundle.putParcelable("barcode_bitmap", source.renderCroppedGreyscaleBitmap());
            message.setData(bundle);
            //Log.d(TAG, "Sending decode succeeded message...");
            message.sendToTarget();
        } else {
            Message message = Message.obtain(handler, R.id.decode_failed);
            message.sendToTarget();
        }
    }

    private enum State {
        //é¢„è§ˆ
        PREVIEW,
        //æˆ?åŠŸ
        SUCCESS,
        //å®Œæˆ?
        DONE
    }
}
