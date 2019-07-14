/** 
 * ??????????
 * @param context ???
 * @param edit    ???
 */
public static void toggleSoftInput(Context context,EditText edit){
  edit.setFocusable(true);
  edit.setFocusableInTouchMode(true);
  edit.requestFocus();
  InputMethodManager inputManager=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
  inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
}
