public static void showIme(View view){
  getInputMethodManager(view.getContext()).showSoftInput(view,0);
}
