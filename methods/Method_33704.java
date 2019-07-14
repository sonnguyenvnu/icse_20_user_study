/** 
 * ?????
 * @param activity ???????activity
 */
public static void hideSoftKeyBoard(Activity activity){
  final View v=activity.getWindow().peekDecorView();
  if (v != null && v.getWindowToken() != null) {
    try {
      ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }
 catch (    Exception e) {
      Log.w("TAG",e.toString());
    }
  }
}
