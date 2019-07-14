private void changeDraweeViewScaleType(SimpleDraweeView draweeView,ScaleType scaleType,@Nullable PointF focusPoint){
  final GenericDraweeHierarchy hierarchy=draweeView.getHierarchy();
  hierarchy.setActualImageScaleType(scaleType);
  hierarchy.setActualImageFocusPoint(focusPoint != null ? focusPoint : new PointF(0.5f,0.5f));
  final RoundingParams roundingParams=Preconditions.checkNotNull(hierarchy.getRoundingParams());
  if (BITMAP_ONLY_SCALETYPES.contains(scaleType)) {
    roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
  }
 else {
    roundingParams.setOverlayColor(mWindowBackgroundColor);
  }
  hierarchy.setRoundingParams(roundingParams);
}
