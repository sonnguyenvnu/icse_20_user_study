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

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**应用设置工具类
 * @author Lemon
 * @must application中在DataKeeper.init();�?�SettingUtil.init(...);
 * @warn 修改�?务器地�?�（URL_SERVER_ADDRESS_NORMAL_HTTP等）
 */
public final class SettingUtil {
	private static final String TAG = "SettingUtil";

	public static final boolean isReleased = false;//应用已�?�布

	/**建议改�?你自己项目的路径*/
	public static final String APP_SETTING = "SHARE_PREFS_" + "APP_SETTING";

	private SettingUtil() {/*�?能实例化**/}


	public static final String KEY_CACHE = "KEY_CACHE";//开�?�缓存
	public static final String KEY_PRELOAD = "KEY_PRELOAD";//开�?�预加载

	public static final String KEY_VOICE = "KEY_VOICE";//开�?�通知声
	public static final String KEY_VIBRATE = "KEY_VIBRATE";//开�?�震动
	public static final String KEY_NO_DISTURB = "KEY_NO_DISTURB";//夜间防打扰

	public static final String KEY_IS_ON_TEST_MODE = "KEY_IS_ON_TEST_MODE";//测试模�?
	public static final String KEY_IS_FIRST_START = "KEY_IS_FIRST_START";//第一次打开应用

	public static final String[] KEYS = {
		KEY_CACHE,
		KEY_PRELOAD,

		KEY_VOICE,
		KEY_VIBRATE,
		KEY_NO_DISTURB,

		KEY_IS_ON_TEST_MODE,
		KEY_IS_FIRST_START,
	};

	public static boolean cache = true;//开�?�缓存
	public static boolean preload = true;//开�?�预加载

	public static boolean voice = true;//开�?�通知声
	public static boolean vibrate = true;//开�?�震动
	public static boolean noDisturb = false;//夜间防打扰

	public static boolean isOnTestMode = false;//测试模�?
	public static boolean isFirstStart = true;//第一次打开应用

	public static final boolean[] defaultValues;
	static {
		defaultValues = new boolean[]{
				cache,//开�?�缓存
				preload,//开�?�预加载

				voice,//开�?�通知声
				vibrate,//开�?�震动
				noDisturb,//夜间防打扰

				isOnTestMode,//测试模�?
				isFirstStart,//第一次打开应用
		};
	}

	private static Context context;
	/**�?始化
	 * @param context_
	 */
	public static void init(Context context_) {
		context = context_;

		cache = getBoolean(KEY_CACHE, cache);
		preload = getBoolean(KEY_PRELOAD, preload);

		voice = getBoolean(KEY_VOICE, voice);
		vibrate = getBoolean(KEY_VIBRATE, vibrate);
		noDisturb = getBoolean(KEY_NO_DISTURB, noDisturb);

		isOnTestMode = getBoolean(KEY_IS_ON_TEST_MODE, isOnTestMode);
		isFirstStart = getBoolean(KEY_IS_FIRST_START, isFirstStart);
	}

	/**�?��?默认
	 */
	public static void restoreDefault() {
		for (int i = 0; i < KEYS.length; i++) {
			putBoolean(KEYS[i], defaultValues[i]);
		}

		init(context);
	}


	/**判断是�?�存在key
	 * @param key
	 * @return
	 */
	public static boolean isContainKey(String key) {
		return getKeyIndex(key) >= 0;
	}

	/**获�?�key在KEYS中的�?置
	 * @param key
	 * @return
	 */
	public static int getKeyIndex(String key) {
		key = StringUtil.getTrimedString(key);
		for (int i = 0; i < KEYS.length; i++) {
			if (key.equals(KEYS[i])) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(String key, boolean defaultValue){
		if (isContainKey(key) == false) {
			Log.e(TAG, "writeBoolean  isContainKey(key) == false >> return defaultValue;");
			return defaultValue;
		}

		return context.getSharedPreferences(APP_SETTING, Context.MODE_PRIVATE).getBoolean(key, defaultValue);
	}


	/**设置所有boolean
	 * @param values
	 */
	public static void putAllBoolean(boolean[] values){
		if (values == null || values.length != KEYS.length) {
			Log.e(TAG, "putAllBoolean  values == null || values.length != KEYS.length >> return;");
			return;
		}

		Editor editor = context.getSharedPreferences(APP_SETTING, Context.MODE_PRIVATE).edit();
		editor.clear();
		for (int i = 0; i < values.length; i++) {
			editor.putBoolean(KEYS[i], values[i]);
		}
		editor.commit();

		init(context);
	}

	/**
	 * @param key
	 * @param value
	 */
	public static void putBoolean(String key, boolean value){
		int keyIndex = getKeyIndex(key);
		if (keyIndex <= 0) {
			Log.e(TAG, "writeBoolean  keyIndex <= 0 >> return;");
			return;
		}

		context.getSharedPreferences(APP_SETTING, Context.MODE_PRIVATE)
		.edit()
		.remove(key)//防止因类型�?�?�导致崩溃
		.putBoolean(key, value)
		.commit();

		init(context);
	}

	/**获�?�所有boolean值
	 * @param context
	 * @return
	 */
	public static boolean[] getAllBooleans(Context context) {
		init(context);
		return new boolean[]{
				cache,
				preload,

				voice,
				vibrate,
				noDisturb,

				isOnTestMode,
				isFirstStart,
		};
	}

	public static final int[] NO_DISTURB_START_TIME = {23, 0};
	public static final int[] NO_DISTURB_END_TIME = {6, 0};

	/**�?打扰
	 * @return
	 */
	public static boolean noDisturb() {
		return getBoolean(KEY_NO_DISTURB, noDisturb)
				&& TimeUtil.isNowInTimeArea(NO_DISTURB_START_TIME, NO_DISTURB_END_TIME);
	}

	/**
	 * TODO 改为你的存图片的�?务器地�?�
	 */
	public static final String IMAGE_BASE_URL = "http://demo.upaiyun.com";

	public static final String KEY_SERVER_ADDRESS_NORMAL = "KEY_SERVER_ADDRESS_NORMAL";
	public static final String KEY_SERVER_ADDRESS_TEST = "KEY_SERVER_ADDRESS_TEST";

	/**
	 * TODO 改为你的正�?�?务器地�?�
	 */
	public static final String URL_SERVER_ADDRESS_NORMAL_HTTP = "http://apijson.cn:8080/";//正�?�?务器
	/**
	 * TODO 改为你的正�?�?务器地�?�
	 */
	public static final String URL_SERVER_ADDRESS_NORMAL_HTTPS = "http://apijson.cn:8080/";//正�?�?务器
	/**
	 * TODO 改为你的测试�?务器地�?�,如果有的�?
	 */
	public static final String URL_SERVER_ADDRESS_TEST = "http://192.168.0.100:8080/";//测试�?务器

	/**获�?�当�?�?务器地�?�
	 * isHttps = false
	 * @return
	 */
	public static String getCurrentServerAddress() {
		return getCurrentServerAddress(false);
	}
	/**获�?�当�?�?务器地�?�
	 * @param isHttps
	 * @return
	 */
	public static String getCurrentServerAddress(boolean isHttps) {
		return isHttps ? URL_SERVER_ADDRESS_NORMAL_HTTPS : getServerAddress(isOnTestMode);
	}
	/**获�?��?务器地�?�
	 * isHttps = false
	 * @param isTest
	 * @return
	 */
	public static String getServerAddress(boolean isTest) {
		return getServerAddress(isTest, false);
	}
	/**获�?��?务器地�?�
	 * @param isTest
	 * @return
	 */
	public static String getServerAddress(boolean isTest, boolean isHttps) {
		SharedPreferences sdf = context.getSharedPreferences(APP_SETTING, Context.MODE_PRIVATE);
		if (sdf == null) {
			return null;
		}
		if (isTest) {
			return sdf.getString(KEY_SERVER_ADDRESS_TEST, URL_SERVER_ADDRESS_TEST);
		}
		return sdf.getString(KEY_SERVER_ADDRESS_NORMAL
				, isHttps ? URL_SERVER_ADDRESS_NORMAL_HTTPS : URL_SERVER_ADDRESS_NORMAL_HTTP);
	}



}
