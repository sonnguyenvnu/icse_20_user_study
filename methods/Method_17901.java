private static void startTemporaryDetach(View view){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    view.cancelPendingInputEvents();
  }
  ViewCompat.dispatchStartTemporaryDetach(view);
}
