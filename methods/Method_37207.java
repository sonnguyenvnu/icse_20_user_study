private int getIndicatorGravity(String gravity){
  if ("left".equals(gravity)) {
    return BannerView.GRAVITY_LEFT;
  }
  if ("right".equals(gravity)) {
    return BannerView.GRAVITY_RIGHT;
  }
  return BannerView.GRAVITY_CENTER;
}
