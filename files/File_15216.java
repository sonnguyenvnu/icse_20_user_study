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
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**通用选择�?�张照片Activity,已自带选择弹窗
 * @author Lemon
 * @use
 * <br> toActivity或startActivityForResult (SelectPictureActivity.createIntent(...), requestCode);
 * <br> 然�?�在onActivityResult方法内
 * <br> data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH); �?�得到图片存储路径
 */
public class SelectPictureActivity extends BaseActivity implements OnClickListener {
	@SuppressWarnings("unused")
	private static final String TAG = "SelectPictureActivity";

	/**
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return new Intent(context, SelectPictureActivity.class);
	}
	
	@Override
	public Activity getActivity() {
		return this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_picture_activity);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initView() {//必须调用
		
	}


	//UI显示区(�?作UI，但�?存在数�?�获�?�或处�?�代�?，也�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private String picturePath = "";
	@Override
	public void initData() {//必须调用
		
	}

	private File cameraFile;
	/**
	 * 照相获�?�图片
	 */
	public void selectPicFromCamera() {
		if (!CommonUtil.isExitsSdcard()) {
			showShortToast("SD�?��?存在，�?能�?照");
			return;
		}

		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 指定调用相机�?照�?�照片的储存路径
		cameraFile = new File(DataKeeper.imagePath, "photo" + System.currentTimeMillis() + ".jpg");
		cameraFile.getParentFile().mkdirs();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
		toActivity(intent, REQUEST_CODE_CAMERA);
	}


	/**
	 * 从图库获�?�图片
	 */
	public void selectPicFromLocal() {
		Intent intent;
		if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
		} else {
			intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		}
		toActivity(intent, REQUEST_CODE_LOCAL);
	}

	public static final String RESULT_PICTURE_PATH = "RESULT_PICTURE_PATH";
	/**根�?�图库图片uri�?��?图片
	 * @param selectedImage
	 */
	private void sendPicByUri(Uri selectedImage) {
		Cursor cursor = getContentResolver().query(selectedImage, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex("_data");
			picturePath = cursor.getString(columnIndex);
			cursor.close();
			cursor = null;

			if (picturePath == null || picturePath.equals("null")) {
				Toast toast = Toast.makeText(this, "找�?到图片", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
		} else {
			File file = new File(selectedImage.getPath());
			if (!file.exists()) {
				Toast toast = Toast.makeText(this, "找�?到图片", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;

			}
			picturePath = file.getAbsolutePath();
		}
		setResult(RESULT_OK, new Intent().putExtra(RESULT_PICTURE_PATH, picturePath));
	}

	//Data数�?�区(存在数�?�获�?�或处�?�代�?，但�?存在事件监�?�代�?)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(�?��?存在事件监�?�代�?就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用
		
		findViewById(R.id.llSelectPictureBg).setOnClickListener(this);

		toActivity(new Intent(context, BottomMenuWindow.class)
		.putExtra(BottomMenuWindow.INTENT_TITLE, "选择图片")
		.putExtra(BottomMenuWindow.INTENT_ITEMS, new String[]{"�?照", "图库"})
		, REQUEST_TO_BOTTOM_MENU, false);
	}

	//系统自带监�?�方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.llSelectPictureBg) {
			finish();
		}
	}



	//类相关监�?�<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final int REQUEST_TO_BOTTOM_MENU = 10;
	public static final int REQUEST_CODE_CAMERA = 18;
	public static final int REQUEST_CODE_LOCAL = 19;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case REQUEST_TO_BOTTOM_MENU:
				if (data != null) {
					switch (data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1)) {
					case 0:
						selectPicFromCamera();//照相
						return;
					case 1:
						selectPicFromLocal();//从图库筛选
						return;
					default:
						break;
					}
				}
				break;
			case REQUEST_CODE_CAMERA: //�?��?照片
				if (cameraFile != null && cameraFile.exists()) {
					picturePath = cameraFile.getAbsolutePath();
					setResult(RESULT_OK, new Intent().putExtra(RESULT_PICTURE_PATH, picturePath));
				}
			case REQUEST_CODE_LOCAL: //�?��?本地图片
				if (data != null) {
					Uri selectedImage = data.getData();
					if (selectedImage != null) {
						sendPicByUri(selectedImage);
					}
				}
				break;
			default:
				break;
			}
		}

		finish();
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
