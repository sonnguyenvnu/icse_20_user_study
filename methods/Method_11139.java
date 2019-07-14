private static void AdjustHorizontalCenteredOutOfBounds(TextView tipView,ViewGroup root,Point point,RxCoordinates rootLocation){
  ViewGroup.LayoutParams params=tipView.getLayoutParams();
  int rootWidth=root.getWidth() - root.getPaddingLeft() - root.getPaddingRight();
  if (tipView.getMeasuredWidth() > rootWidth) {
    point.x=rootLocation.left + root.getPaddingLeft();
    params.width=rootWidth;
    params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
    tipView.setLayoutParams(params);
    measureViewWithFixedWidth(tipView,rootWidth);
  }
}
