private static void unsetViewBackground(View view,ViewNodeInfo viewNodeInfo){
  final ComparableDrawable background=viewNodeInfo.getBackground();
  if (background != null) {
    setBackgroundCompat(view,null);
  }
}
