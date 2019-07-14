/** 
 * ???????
 * @param activity
 * @param view
 */
public static void hideKeyboard(Activity activity,View view){
  InputMethodManager imm=(InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
  imm.hideSoftInputFromWindow(view.getWindowToken(),0);
}
