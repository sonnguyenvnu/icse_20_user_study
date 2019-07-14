private void setShowBorder(SimpleDraweeView draweeView,boolean show,boolean scaleInside){
  final RoundingParams roundingParams=Preconditions.checkNotNull(draweeView.getHierarchy().getRoundingParams());
  if (show) {
    roundingParams.setBorder(mColorPrimary,getResources().getDimensionPixelSize(R.dimen.drawee_rounded_corners_border_width));
    roundingParams.setScaleDownInsideBorders(scaleInside);
  }
 else {
    roundingParams.setBorder(Color.TRANSPARENT,0);
  }
  draweeView.getHierarchy().setRoundingParams(roundingParams);
}
