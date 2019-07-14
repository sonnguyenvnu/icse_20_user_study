/** 
 * ?????
 */
public static void showSoftKeyBoard(Activity activity,View view){
  ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view,0);
}
