public static boolean showKeyboard(View view){
  if (view == null) {
    return false;
  }
  try {
    InputMethodManager inputManager=(InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    return inputManager.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return false;
}
