/** 
 * ????? Shared Element???? ?????????????? ??????setHeaderPicView()?????????isShow???true
 * @param imageView ?????
 * @param isShow    ????????
 */
protected void setMotion(ImageView imageView,boolean isShow){
  if (!isShow) {
    return;
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    ArcMotion arcMotion=new ArcMotion();
    arcMotion.setMinimumHorizontalAngle(10f);
    arcMotion.setMinimumVerticalAngle(10f);
    Interpolator interpolator=AnimationUtils.loadInterpolator(this,android.R.interpolator.fast_out_slow_in);
    changeBounds=new CustomChangeBounds();
    changeBounds.setPathMotion(arcMotion);
    changeBounds.setInterpolator(interpolator);
    changeBounds.addTarget(imageView);
    getWindow().setSharedElementEnterTransition(changeBounds);
    getWindow().setSharedElementReturnTransition(changeBounds);
  }
}
