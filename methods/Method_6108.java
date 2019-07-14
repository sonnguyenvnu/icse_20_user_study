public static void hideKeyboard(View view){
  if (view == null) {
    return;
  }
  try {
    InputMethodManager imm=(InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    if (!imm.isActive()) {
      return;
    }
    imm.hideSoftInputFromWindow(view.getWindowToken(),0);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
