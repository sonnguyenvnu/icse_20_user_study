package com.vondear.rxdemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.vondear.rxdemo.R;
import com.vondear.rxtool.RxFileTool;
import com.vondear.rxtool.RxZipTool;
import com.vondear.rxtool.view.RxToast;
import com.vondear.rxui.activity.ActivityBase;
import com.vondear.rxui.view.RxTitle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author vondear
 */
public class ActivityZipEncrypt extends ActivityBase {

    @BindView(R.id.btn_create_folder)
    Button mBtnCreateFolder;
    @BindView(R.id.btn_zip)
    Button mBtnZip;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.rx_title)
    RxTitle mRxTitle;
    @BindView(R.id.btn_upzip)
    Button mBtnUpzip;
    @BindView(R.id.btn_zip_delete_dir)
    Button mBtnZipDeleteDir;
    @BindView(R.id.Progress)
    ProgressBar mProgress;

    private File fileDir;
    private File fileTempDir;
    private File unZipDirFile;
    private File fileZip;

    private String zipPath;
    private String zipParentPath;
    private String zipTempDeletePath;
    private String unzipPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_encrypt);
        ButterKnife.bind(this);
        mRxTitle.setLeftFinish(mContext);
        zipParentPath = RxFileTool.getRootPath().getAbsolutePath() + File.separator + "RxTool";
        zipTempDeletePath = RxFileTool.getRootPath().getAbsolutePath() + File.separator + "RxTool" + File.separator + "RxTempTool";
        unzipPath = RxFileTool.getRootPath().getAbsolutePath() + File.separator + "解压缩文件夹";
        zipPath = RxFileTool.getRootPath().getAbsolutePath() + File.separator + "Rxtool.zip";

        unZipDirFile = new File(unzipPath);
        if (!unZipDirFile.exists()) {
            unZipDirFile.mkdirs();
        }
    }

    @OnClick({R.id.btn_create_folder, R.id.btn_zip, R.id.btn_upzip, R.id.btn_zip_delete_dir})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_folder:
                fileDir = new File(zipParentPath);
                fileTempDir = new File(zipTempDeletePath);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }

                if (!fileTempDir.exists()) {
                    fileTempDir.mkdirs();
                }

                try {
                    File file = File.createTempFile("被压缩文件�?ε�?", ".txt", fileDir);
                    File file1 = File.createTempFile("待删除文件o(╥�?╥)o", ".txt", fileTempDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mTvState.setText("临时文件 创建�?功,文件�?于根目录的RxTool里(✺ω✺)");
                break;
            case R.id.btn_zip:
                fileZip = new File(zipPath);
                if (fileZip.exists()) {
                    RxFileTool.deleteFile(fileZip);
                    Logger.d("导出文件已存在，将之删除");
                }

                if (fileDir != null) {
                    if (fileDir.exists()) {
                        String result = RxZipTool.zipEncrypt(fileDir.getAbsolutePath(), fileZip.getAbsolutePath(), true, "123456");
                        mTvState.setText("压缩并加密�?功,路径" + result);
                    } else {
                        RxToast.error("导出的文件�?存在");
                    }
                } else {
                    RxToast.error("导出的文件�?存在");
                }
                break;
            case R.id.btn_upzip:
                List<File> zipFiles = RxZipTool.unzipFileByKeyword(fileZip, unZipDirFile, "123456");
                String str = "导出文件列表(*▽*)\n";
                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        str += zipFile.getAbsolutePath() + "\n\n";
                    }
                }
                mTvState.setText(str);

//                RxZipTool.Unzip(fileZip, unZipDirFile.getAbsolutePath(), "123456", "GBK", _handler, false);
                break;
            case R.id.btn_zip_delete_dir:
                if (RxZipTool.removeDirFromZipArchive(zipPath, "RxTool" + File.separator + "RxTempTool")) {
                    mTvState.setText("RxTempTool 删除�?功");
                } else {
                    mTvState.setText("RxTempTool 删除失败");
                }
                break;
            default:
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler _handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RxZipTool.CompressStatus.START: {
                    mTvState.setText("Start...");
                    mProgress.setVisibility(View.VISIBLE);
                    break;
                }
                case RxZipTool.CompressStatus.HANDLING: {
                    Bundle bundle = msg.getData();
                    int percent = bundle.getInt(RxZipTool.CompressKeys.PERCENT);
                    mTvState.setText(percent + "%");
                    mProgress.setProgress(percent);
                    break;
                }
                case RxZipTool.CompressStatus.ERROR: {
                    Bundle bundle = msg.getData();
                    String error = bundle.getString(RxZipTool.CompressKeys.ERROR);
                    mTvState.setText(error);
                    break;
                }
                case RxZipTool.CompressStatus.COMPLETED: {
                    mTvState.setText("Completed");
                    mProgress.setVisibility(View.INVISIBLE);
                    break;
                }
                default:
                    break;
            }
        }
    };
}
