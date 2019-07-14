/** 
 * ??/?????
 * @param context
 * @param et
 * @param toGetWindowTokenView(?null?toGetWindowTokenView = et) ??et??View?????toGetWindowTokenView??????/??
 * @param show
 */
public static void showKeyboard(Context context,EditText et,View toGetWindowTokenView,boolean show){
  if (context == null) {
    Log.e(TAG,"showKeyboard  context == null >> return;");
    return;
  }
  imm=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
  if (toGetWindowTokenView == null) {
    Log.w(TAG,"showKeyboard   toGetWindowTokenView == null");
    toGetWindowTokenView=et;
  }
  if (toGetWindowTokenView == null) {
    Log.e(TAG,"showKeyboard  toGetWindowTokenView == null && et == null  >> return;");
    return;
  }
  if (show == false) {
    imm.hideSoftInputFromWindow(toGetWindowTokenView.getWindowToken(),0);
    if (et != null) {
      et.clearFocus();
    }
  }
 else {
    if (et != null) {
      et.setFocusable(true);
      et.setFocusableInTouchMode(true);
      et.requestFocus();
      imm.toggleSoftInputFromWindow(toGetWindowTokenView.getWindowToken(),InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
  }
}
