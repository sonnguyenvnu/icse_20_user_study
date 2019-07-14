public static void hideKeyboard(final @NonNull Context context,final @Nullable View view){
  final InputMethodManager inputManager=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
  final IBinder windowToken=view != null ? view.getWindowToken() : null;
  inputManager.hideSoftInputFromWindow(windowToken,InputMethodManager.HIDE_NOT_ALWAYS);
}
