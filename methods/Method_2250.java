@Override @Nullable public Drawable buildOverlayDrawable(Resources resources,ImageOptions imageOptions){
  int resId=imageOptions.getOverlayRes();
  return resId == 0 ? null : resources.getDrawable(imageOptions.getOverlayRes());
}
