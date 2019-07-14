public static void hideKeyboard(@NonNull View view){
  InputMethodManager inputManager=(InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
  if (inputManager != null) {
    inputManager.hideSoftInputFromWindow(view.getWindowToken(),0);
  }
}
