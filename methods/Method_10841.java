/** 
 * ???????
 * @param context ???
 * @param edit    ???
 */
public static void hideSoftInput(Context context,EditText edit){
  edit.clearFocus();
  InputMethodManager inputmanger=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
  inputmanger.hideSoftInputFromWindow(edit.getWindowToken(),0);
}
