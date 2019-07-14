private static void setViewBackground(View view,ViewNodeInfo viewNodeInfo){
  final ComparableDrawable background=viewNodeInfo.getBackground();
  if (background != null) {
    setBackgroundCompat(view,background);
  }
}
