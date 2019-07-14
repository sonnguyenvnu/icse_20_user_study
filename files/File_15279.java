/*Copyright Â©2015 TommyLemon(https://github.com/TommyLemon)

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
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**é€šç”¨å¯†ç ?ã€?æ‰‹æœºå?·ã€?éªŒè¯?ç ?è¾“å…¥æ¡†è¾“å…¥å­—ç¬¦åˆ¤æ–­å?Šé”™è¯¯æ??ç¤º ç±»
 * @author Lemon
 * @use EditTextUtil.xxxMethod(...);
 */
public class EditTextUtil {
	private static final String TAG = "EditTextUtil";


	//æ˜¾ç¤º/éš?è—?è¾“å…¥æ³•<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**éš?è—?è¾“å…¥æ³•
	 * @param context
	 * @param toGetWindowTokenView
	 */
	public static void hideKeyboard(Context context, View toGetWindowTokenView){
		showKeyboard(context, null, toGetWindowTokenView, false);
	}
	/**æ˜¾ç¤ºè¾“å…¥æ³•
	 * @param context
	 * @param et
	 */
	public static void showKeyboard(Context context, EditText et){
		showKeyboard(context, et, true);
	}
	/**æ˜¾ç¤º/éš?è—?è¾“å…¥æ³•
	 * @param context
	 * @param et
	 * @param show
	 */
	public static void showKeyboard(Context context, EditText et, boolean show){
		showKeyboard(context, et, null, show);
	}
	/**æ˜¾ç¤ºè¾“å…¥æ³•
	 * @param context
	 * @param et
	 * @param toGetWindowTokenView(ä¸ºnullæ—¶toGetWindowTokenView = et) åŒ…å?«etçš„çˆ¶Viewï¼Œé”®ç›˜æ ¹æ?®toGetWindowTokenViewçš„ä½?ç½®æ?¥å¼¹å‡º/éš?è—?
	 */
	public static void showKeyboard(Context context, EditText et, View toGetWindowTokenView) {
		showKeyboard(context, et, toGetWindowTokenView, true);
	}
	/**æ˜¾ç¤º/éš?è—?è¾“å…¥æ³•
	 * @param context
	 * @param et
	 * @param toGetWindowTokenView(ä¸ºnullæ—¶toGetWindowTokenView = et) åŒ…å?«etçš„çˆ¶Viewï¼Œé”®ç›˜æ ¹æ?®toGetWindowTokenViewçš„ä½?ç½®æ?¥å¼¹å‡º/éš?è—?
	 * @param show
	 */
	public static void showKeyboard(Context context, EditText et, View toGetWindowTokenView, boolean show){
		if (context == null) {
			Log.e(TAG, "showKeyboard  context == null >> return;");
			return;
		}

		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);//immå¿…é¡»ä¸Žcontextå”¯ä¸€å¯¹åº”
		if (toGetWindowTokenView == null) {
			Log.w(TAG, "showKeyboard   toGetWindowTokenView == null");
			toGetWindowTokenView = et;
		}
		if (toGetWindowTokenView == null) {
			Log.e(TAG, "showKeyboard  toGetWindowTokenView == null && et == null  >> return;");
			return;
		}

		if (show == false) {
			imm.hideSoftInputFromWindow(toGetWindowTokenView.getWindowToken(), 0);
			if (et != null) {
				et.clearFocus();
			}
		} else {
			if (et != null) {
				et.setFocusable(true);
				et.setFocusableInTouchMode(true);
				et.requestFocus();
				imm.toggleSoftInputFromWindow(toGetWindowTokenView.getWindowToken()
						, InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
			}
		}
	}

	//æ˜¾ç¤º/éš?è—?è¾“å…¥æ³•>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//å¯¹è¾“å…¥å­—ç¬¦åˆ¤æ–­<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public final static int TYPE_NOT_ALLOWED_EMPTY = 0;
	public final static int TYPE_VERIFY = 1;
	public final static int TYPE_PASSWORD = 2;
	public final static int TYPE_PHONE = 3;
	public final static int TYPE_MAIL = 4;

	private static ColorStateList oringinalHintColor;

	/**åˆ¤æ–­edittextè¾“å…¥æ–‡å­—æ˜¯å?¦å?ˆæ³•
	 * @param context
	 * @param et
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et) {
		return isInputedCorrect(context, et, TYPE_NOT_ALLOWED_EMPTY, null);
	}
	/**åˆ¤æ–­edittextè¾“å…¥æ–‡å­—æ˜¯å?¦å?ˆæ³•
	 * @param context
	 * @param et
	 * @param errorRemind
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et, String errorRemind) {
		return isInputedCorrect(context, et, TYPE_NOT_ALLOWED_EMPTY, errorRemind);
	}
	/**åˆ¤æ–­edittextè¾“å…¥æ–‡å­—æ˜¯å?¦å?ˆæ³•
	 * @param context
	 * @param et
	 * @param type
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et, int type) {
		return isInputedCorrect(context, et, type, null);
	}
	/**åˆ¤æ–­edittextè¾“å…¥æ–‡å­—æ˜¯å?¦å?ˆæ³•
	 * @param context
	 * @param stringResId
	 * @param et
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, int stringResId, EditText et) {
		return isInputedCorrect(context, et, TYPE_NOT_ALLOWED_EMPTY, stringResId);
	}
	/**åˆ¤æ–­edittextè¾“å…¥æ–‡å­—æ˜¯å?¦å?ˆæ³•
	 * @param context
	 * @param et
	 * @param type
	 * @param stringResId
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et, int type, int stringResId) {
		try {
			if (context != null && stringResId > 0) {
				return isInputedCorrect(context, et, type, context.getResources().getString(stringResId));
			}
		} catch (Exception e) {
			Log.e(TAG, "isInputedCorrect try { if (context != null && stringResId > 0) {catch (Exception e) \n" + e.getMessage());
		}
		return false;
	}
	/**åˆ¤æ–­edittextè¾“å…¥æ–‡å­—æ˜¯å?¦å?ˆæ³•
	 * @param context
	 * @param et
	 * @param type
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et, int type, String errorRemind) {
		if (context == null || et == null) {
			Log.e(TAG, "isInputedCorrect context == null || et == null >> return false;");
			return false;
		}
		oringinalHintColor = et.getHintTextColors();

		String inputed = StringUtil.getTrimedString(et);
		switch (type) {
			case TYPE_VERIFY:
				if (type == TYPE_VERIFY && inputed.length() < 4) {
					return showInputedError(context, et, StringUtil.isNotEmpty(errorRemind, true) ? errorRemind : "éªŒè¯?ç ?ä¸?èƒ½å°?äºŽ4ä½?");
				}
				break;
			case TYPE_PASSWORD:
				if (inputed.length() < 6) {
					return showInputedError(context, et, StringUtil.isNotEmpty(errorRemind, true) ? errorRemind : "å¯†ç ?ä¸?èƒ½å°?äºŽ6ä½?");
				}
				if (StringUtil.isNumberOrAlpha(inputed) == false) {
					return showInputedError(context, et, StringUtil.isNotEmpty(errorRemind, true) ? errorRemind : "å¯†ç ?å?ªèƒ½å?«æœ‰å­—æ¯?æˆ–æ•°å­—");
				}
				break;
			case TYPE_PHONE:
				if (inputed.length() != 11) {
					return showInputedError(context, et, StringUtil.isNotEmpty(errorRemind, true) ? errorRemind : "è¯·è¾“å…¥11ä½?æ‰‹æœºå?·");
				}
				if (StringUtil.isPhone(inputed) == false) {
					Toast.makeText(context, "æ‚¨è¾“å…¥çš„æ‰‹æœºå?·æ ¼å¼?ä¸?å¯¹å“¦~", Toast.LENGTH_SHORT).show();
					return false;
				}
				break;
			case TYPE_MAIL:
				if (StringUtil.isEmail(inputed) == false) {
					return showInputedError(context, "æ‚¨è¾“å…¥çš„é‚®ç®±æ ¼å¼?ä¸?å¯¹å“¦~");
				}
				break;
			default:
				if (StringUtil.isNotEmpty(inputed, true) == false || inputed.equals(StringUtil.getTrimedString(et.getHint()))) {
					return showInputedError(context, et, StringUtil.isNotEmpty(errorRemind, true) ? errorRemind : StringUtil.getTrimedString(et));
				}
				break;
		}

		et.setHintTextColor(oringinalHintColor);
		return true;
	}




	/**å­—ç¬¦ä¸?å?ˆæ³•æ??ç¤º(toast)
	 * @param context
	 * @param resId
	 * @return
	 */
	public static boolean showInputedError(Activity context, int resId) {
		return showInputedError(context, null, resId);
	}
	/**å­—ç¬¦ä¸?å?ˆæ³•æ??ç¤º(et == null ? toast : hint)
	 * @param context
	 * @param et
	 * @param resId
	 * @return
	 */
	public static boolean showInputedError(Activity context, EditText et, int resId) {
		try {
			return showInputedError(context, et, context.getResources().getString(resId));
		} catch (Exception e) {
			Log.e(TAG, "" + e.getMessage());
		}
		return false;
	}
	/**å­—ç¬¦ä¸?å?ˆæ³•æ??ç¤º(toast)
	 * @param context
	 * @param string
	 * @return
	 */
	public static boolean showInputedError(Activity context, String string) {
		return showInputedError(context, null, string);
	}
	/**å­—ç¬¦ä¸?å?ˆæ³•æ??ç¤º(et == null ? toast : hint)
	 * @param context
	 * @param et
	 * @param string
	 * @return
	 */
	public static boolean showInputedError(Activity context, EditText et, String string) {
		if (context == null || StringUtil.isNotEmpty(string, false) == false) {
			Log.e(TAG, "showInputedError  context == null || et == null || StringUtil.isNotEmpty(string, false) == false >> return false;");
			return false;
		}
		if (et == null) {
			Toast.makeText(context, StringUtil.getTrimedString(string), Toast.LENGTH_SHORT).show();
		} else {
			et.setText("");
			et.setHint(string);
			et.setHintTextColor(context.getResources().getColor(R.color.red));
		}
		return false;
	}

	//å¯¹è¾“å…¥å­—ç¬¦åˆ¤æ–­>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
