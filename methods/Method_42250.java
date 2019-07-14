private static void hideKeyboard(Context context,IBinder token){
  InputMethodManager imm=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
  imm.hideSoftInputFromWindow(token,0);
}
