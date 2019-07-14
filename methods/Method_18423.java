private static void unsetViewForeground(View view,ViewNodeInfo viewNodeInfo){
  final Drawable foreground=viewNodeInfo.getForeground();
  if (foreground != null) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      throw new IllegalStateException("MountState has a ViewNodeInfo with foreground however " + "the current Android version doesn't support foreground on Views");
    }
    view.setForeground(null);
  }
}
