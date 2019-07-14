public static boolean isKeyboardShowed(View view){
  if (view == null) {
    return false;
  }
  try {
    InputMethodManager inputManager=(InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    return inputManager.isActive(view);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return false;
}
