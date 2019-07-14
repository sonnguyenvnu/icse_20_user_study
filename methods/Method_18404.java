private static void unsetOutlineProvider(View view,ViewOutlineProvider outlineProvider){
  if (outlineProvider != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    view.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
  }
}
