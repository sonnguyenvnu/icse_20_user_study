/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package zuo.biao.library.ui;

import java.io.File;

import zuo.biao.library.R;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.DataKeeper;
import zuo.biao.library.util.StringUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

/**通用获�?��?剪�?�张照片Activity
 * @author Lemon
 * @use
 * <br> toActivity或startActivityForResult (CutPictureActivity.createIntent(...), requestCode);
 * <br> 然�?�在onActivityResult方法内
 * <br> data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH); �?�得到图片存储路径
 */
public class CutPictureActivity extends BaseActivity {
	private static final String TAG = "CutPictureActivity";

	//�?�动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final String INTENT_ORIGINAL_PICTURE_PATH = "INTENT_ORIGINAL_PICTURE_PATH";
	public static final String INTENT_CUTTED_PICTURE_PATH = "INTENT_CUTTED_PICTURE_PATH";
	public static final String INTENT_CUTTED_PICTURE_NAME = "INTENT_CUTTED_PICTURE_NAME";

	public static final String INTENT_CUT_WIDTH = "INTENT_CUT_WIDTH";
	public static final String INTENT_CUT_HEIGHT = "INTENT_CUT_HEIGHT";

	/**�?�动这个Activity的Intent
	 * @param context
	 * @param originalPath
	 * @param cuttedPath
	 * @param cuttedName
	 * @param cuttedSize
	 * @return
	 */
	public static Intent createIntent(Context context, String originalPath
			, String cuttedPath, String cuttedName, int cuttedSize) {
		return createIntent(context, originalPath, cuttedPath, cuttedName, cuttedSize, cuttedSize);
	}
	/**�?�动这个Activity的Intent
	 * @param context
	 * @param originalPath
	 * @param cuttedPath
	 * @param cuttedName
	 * @param cuttedWidth
	 * @param cuttedHeight
	 * @return
	 */
	public static Intent createIntent(Context context, String originalPath
			, String cuttedPath, String cuttedName, int cuttedWidth, int cuttedHeight) {
		Intent intent = new Intent(context, CutPictureActivity.class);
		intent.putExtra(INTENT_ORIGINAL_PICTURE_PATH, originalPath);
		intent.putExtra(INTENT_CUTTED_PICTURE_PATH, cuttedPath);
		intent.putExtra(INTENT_CUTTED_PICTURE_NAME, cuttedName);
		intent.putExtra(INTENT_CUT_WIDTH, cuttedWidth);
		intent.putExtra(INTENT_CUT_HEIGHT, cuttedHeight);
		return intent;
	}

	//�?�动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Override
	public Activity getActivity() {
		return this;
	}

	
	private String originalPicturePath;
	private String cuttedPicturePath;
	private String cuttedPictureName;
	private int cuttedWidth;
	private int cuttedHeight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		intent = getIntent();

		originalPicturePath = intent.getStringExtra(INTENT_ORIGINAL_PICTURE_PATH);
		cuttedWidth = intent.getIntExtra(INTENT_CUT_WIDTH, 0);
		cuttedHeight = intent.getIntExtra(INTENT_CUT_HEIGHT, 0);
		if (cuttedWidth <= 0) {
			cuttedWidth = cuttedHeight;
		}
		if (cuttedHeight <= 0) {
			cuttedHeight = cuttedWidth;
		}

		if (StringUtil.isNotEmpty(originalPicturePath, true) == false || cuttedWidth <= 0) {
			Log.e(TAG, "onCreate  StringUtil.isNotEmpty(originalPicturePath, true)" +
					" == false || cuttedWidth <= 0 >> finish(); return;");
			showShortToast("图片�?存在，请先选择图片");
			finish();
			return;
		}

		//功能归类分区方法，必须调用<<<<<<<<<<
		initData();
		initView();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initView() {//必须调用
		
	}


	/**照片�?剪
	 * @param path
	 * @param width
	 * @param height
	 */
	public void startPhotoZoom(String path, int width, int height) {
		startPhotoZoom(Uri.fromFile(new File(path)), width, height);
	}
	/**照片�?剪
	 * @param fileUri
	 * @param width
	 * @param height
	 */
	public void startPhotoZoom(Uri fileUri, int width, int height) {

		intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(fileUri, "image/*");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY 是剪�?图片的宽高
		intent.putExtra("outputX", width);
		intent.putExtra("outputY", height);

		if (Build.VERSION.SDK_INT >= 23) {
			File outputImage = new File(DataKeeper.imagePath, "output_image" + System.currentTimeMillis() + ".jpg"); 
			cuttedPicturePath = outputImage.getAbsolutePath();
			intent.putExtra("scale", true);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
		} else {
			intent.putExtra("crop", "true");// crop为true是设置在开�?�的intent中设置显示的view�?�以剪�?
			intent.putExtra("return-data", true);
		}
		Log.i(TAG, "startPhotoZoom  fileUri = "+ fileUri);
		toActivity(intent, REQUEST_CUT_PHOTO);
	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	@Override
	public void initData() {//必须调用
		
		startPhotoZoom(originalPicturePath, cuttedWidth, cuttedHeight);
	}



	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用
		
	}

	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final int REQUEST_CODE_CAMERA = 18;
	public static final int REQUEST_CODE_LOCAL = 19;
	public static final int REQUEST_CUT_PHOTO = 20;

	public static final String RESULT_PICTURE_PATH = "RESULT_PICTURE_PATH";
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case REQUEST_CUT_PHOTO: //�?��?本地图片
				if (data != null) {
					if (Build.VERSION.SDK_INT < 23 || new File(cuttedPicturePath).exists() == false) {
						Bundle bundle = data.getExtras();
						if (bundle != null) {
							Bitmap photo = bundle.getParcelable("data");
							//photo.
							if (photo != null) {
								//照片的路径
								setCuttedPicturePath();
								cuttedPicturePath = CommonUtil.savePhotoToSDCard(cuttedPicturePath, cuttedPictureName, "jpg", photo);
							}
						} 
					}
					setResult(RESULT_OK, new Intent().putExtra(RESULT_PICTURE_PATH, cuttedPicturePath));
				} 
				break;
			default:
				break;
			}
		}

		finish();
	}

	private String setCuttedPicturePath() {
		//oringlePicturePath �?对
		cuttedPicturePath = intent.getStringExtra(INTENT_CUTTED_PICTURE_PATH);
		if (StringUtil.isFilePath(cuttedPicturePath) == false) {
			cuttedPicturePath = DataKeeper.fileRootPath + DataKeeper.imagePath;
		}
		cuttedPictureName = intent.getStringExtra(INTENT_CUTTED_PICTURE_NAME);
		if (StringUtil.isFilePath(cuttedPictureName) == false) {
			cuttedPictureName = "photo" + System.currentTimeMillis();
		}
		return cuttedPicturePath;
	}

	@Override
	public void finish() {
		exitAnim = enterAnim = R.anim.null_anim;
		super.finish();
	}

	//类相关监�?�>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监�?�方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(�?��?存在事件监�?�代�?就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//内部类,尽�?少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽�?少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
