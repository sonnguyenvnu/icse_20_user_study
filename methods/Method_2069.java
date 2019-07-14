private void changeMainDraweeScaleType(ScaleType scaleType,@Nullable PointF focusPoint){
  final GenericDraweeHierarchy hierarchy=mDraweeMain.getHierarchy();
  hierarchy.setActualImageScaleType(scaleType);
  hierarchy.setActualImageFocusPoint(focusPoint != null ? focusPoint : new PointF(0.5f,0.5f));
}
