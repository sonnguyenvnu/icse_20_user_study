@TargetApi(Build.VERSION_CODES.LOLLIPOP) public static void postponeTransition(Activity activity){
  if (!shouldEnableTransition()) {
    return;
  }
  ActivityCompat.postponeEnterTransition(activity);
}
