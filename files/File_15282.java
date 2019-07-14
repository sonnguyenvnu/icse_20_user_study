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

package zuo.biao.library.util;

import zuo.biao.library.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**图片加载工具类
 * @author Lemon
 * @use ImageLoaderUtil.loadImage(...)
 */
public class ImageLoaderUtil {
	private static final String TAG = "ImageLoaderUtil";

	private static ImageLoader imageLoader;
	/**�?始化方法
	 * @must 使用其它方法�?必须调用，建议在自定义Application的onCreate方法中调用
	 * @param context
	 */
	public static void init(Context context) {
		if (context == null) {
			Log.e(TAG, "\n\n\n\n\n !!!!!!  <<<<<< init  context == null >> return; >>>>>>>> \n\n\n\n");
			return;
		}
		imageLoader = ImageLoader.getInstance();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
		.defaultDisplayImageOptions(getOption(0))
		// .threadPoolSize(5)
		// //.threadPriority(Thread.MIN_PRIORITY + 3)
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		// .discCacheSize((int)(Runtime.getRuntime().maxMemory()/2))
		// .discCache(new UnlimitedDiscCache(getCachePath()))
		// .memoryCacheSize(2 * 1024 * 1024)
		// .memoryCacheExtraOptions(147, 147)
		// .writeDebugLogs()
		// .httpConnectTimeout(5000)
		// .httpReadTimeout(20000)
		.diskCacheExtraOptions(ScreenUtil.getScreenWidth(context), ScreenUtil.getScreenHeight(context), null)
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.diskCacheSize(50 * 1024 * 1024) // 50 Mb
		// .displayer(new RoundedBitmapDisplayer(5))
		.build();

		imageLoader.init(config);
	}


	/**加载图片
	 * 加载�?图应�?调用该方法�?使用getSmallUri处�?�uri
	 * type = TYPE_DEFAULT
	 * @param iv
	 * @param uri 网�?�url或本地路径path
	 */
	public static void loadImage(ImageView iv, String uri) {
		loadImage(iv, uri, TYPE_DEFAULT);
	}

	public static final int TYPE_DEFAULT = 0;//矩形
	public static final int TYPE_ROUND_CORNER = 1;//圆角矩形
	public static final int TYPE_OVAL = 2;//圆形
	/**加载图片
	 * 加载�?图应�?调用该方法�?使用getSmallUri处�?�uri
	 * @param type 图片显示类型
	 * @param iv
	 * @param uri 网�?�url或本地路径path
	 */
	public static void loadImage(final ImageView iv, String uri, final int type) {
		if (iv == null) {// || iv.getWidth() <= 0) {
			Log.i(TAG, "loadImage  iv == null >> return;");
			return;
		}
		Log.i(TAG, "loadImage  iv" + (iv == null ? "==" : "!=") + "null; uri=" + uri);

		uri = getCorrectUri(uri);

		//新的加载图片
		imageLoader.displayImage(uri, iv, new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View arg1) {
			}
			@Override
			public void onLoadingFailed(String imageUri, View arg1, FailReason arg2) {
			}
			@Override
			public void onLoadingComplete(String imageUri, View arg1, Bitmap loadedImage) {
				switch (type) {
				case TYPE_OVAL:
					iv.setImageBitmap(toRoundCorner(loadedImage, loadedImage.getWidth()/2));
					break;
				case TYPE_ROUND_CORNER:
					iv.setImageBitmap(toRoundCorner(loadedImage, 10));
					break;
				default:
					iv.setImageBitmap(loadedImage);
					break;
				}
			}
			@Override
			public void onLoadingCancelled(String imageUri, View arg1) {
			}
		});

	}


	public static final String FILE_PATH_PREFIX = StringUtil.FILE_PATH_PREFIX;

	/**如果需�?加载�?图请修改为�?图实际标识
	 */
	public static String URL_SUFFIX_SMALL = "!common";
	/**获�?��?�用的uri
	 * @param uri
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String getCorrectUri(String uri) {
		Log.i(TAG, "<<<<  getCorrectUri  uri = " + uri);
		uri = StringUtil.getNoBlankString(uri);

		if (uri.toLowerCase().startsWith(StringUtil.HTTP) == false) {
			uri = uri.startsWith(FILE_PATH_PREFIX) ? uri : FILE_PATH_PREFIX + uri;
		}

		Log.i(TAG, "getCorrectUri  return uri = " + uri + " >>>>> ");
		return uri;
	}


	/**获�?��?置
	 * @param cornerRadiusSize
	 * @return
	 */
	private static DisplayImageOptions getOption(int cornerRadiusSize) {
		return getOption(cornerRadiusSize, cornerRadiusSize <= 0
				? R.drawable.square_alpha : R.drawable.oval_alpha);
	}
	/**获�?��?置
	 * @param cornerRadiusSize
	 * @param defaultImageResId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static DisplayImageOptions getOption(int cornerRadiusSize, int defaultImageResId) {
		Options options0 = new Options();
		options0.inPreferredConfig = Bitmap.Config.RGB_565;

		DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
		if(defaultImageResId > 0) {
			try {
				builder.showImageForEmptyUri(defaultImageResId)
				.showImageOnLoading(defaultImageResId)
				.showImageOnFail(defaultImageResId);
			} catch (Exception e) {
				Log.e(TAG, "getOption  try {builder.showImageForEmptyUri(defaultImageResId) ..." +
						" >> } catch (Exception e) { \n" + e.getMessage());
			}
		}
		if (cornerRadiusSize > 0) {
			builder.displayer(new RoundedBitmapDisplayer(cornerRadiusSize));
		}

		return builder.cacheInMemory(true).cacheOnDisc(true).decodingOptions(options0).build();
	}

	/**获�?��?图url或path
	 * path�?加URL_SUFFIX_SMALL
	 * @param uri
	 * @return
	 */
	public static String getSmallUri(String uri) {
		return getSmallUri(uri, false);
	}
	/**获�?��?图url或path
	 * path�?加URL_SUFFIX_SMALL
	 * @param uri
	 * @param isLocalPath
	 * @return
	 */
	public static String getSmallUri(String uri, boolean isLocalPath) {
		if (uri == null) {
			Log.e(TAG, "getSmallUri  uri == null >> return null;");
			return null;
		}

		if (uri.startsWith("/") || uri.startsWith(FILE_PATH_PREFIX) || StringUtil.isFilePathExist(FILE_PATH_PREFIX + uri)) {
			isLocalPath = true;
		}
		return isLocalPath || uri.endsWith(URL_SUFFIX_SMALL)
				? uri : uri + URL_SUFFIX_SMALL;
	}

	/**将图片改为圆角类型
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

}
