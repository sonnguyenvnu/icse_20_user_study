private static void unsetClipToOutline(View view,boolean clipToOutline){
  if (clipToOutline && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    view.setClipToOutline(false);
  }
}
