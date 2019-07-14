@SuppressWarnings("WeakerAccess") public static void showKeyboard(@NonNull View v,@NonNull Context activity){
  InputMethodManager imm=(InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
  imm.showSoftInput(v,0);
}
