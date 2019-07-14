public static void hideIme(View view){
  getInputMethodManager(view.getContext()).hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
}
