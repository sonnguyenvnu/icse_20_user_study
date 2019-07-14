/** 
 * ???????
 * @param activity activity
 */
public static void hideSoftInput(Activity activity){
  View view=activity.getWindow().peekDecorView();
  if (view != null) {
    InputMethodManager inputmanger=(InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    inputmanger.hideSoftInputFromWindow(view.getWindowToken(),0);
  }
}
