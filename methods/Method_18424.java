private static void unsetViewLayoutDirection(View view){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
    return;
  }
  view.setLayoutDirection(View.LAYOUT_DIRECTION_INHERIT);
}
