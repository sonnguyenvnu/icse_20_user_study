private static void AdjustHorizontalLeftAlignmentOutOfBounds(TextView tipView,ViewGroup root,Point point,RxCoordinates anchorViewRxCoordinates,RxCoordinates rootLocation){
  ViewGroup.LayoutParams params=tipView.getLayoutParams();
  int rootRight=rootLocation.right - root.getPaddingRight();
  if (point.x + tipView.getMeasuredWidth() > rootRight) {
    params.width=rootRight - anchorViewRxCoordinates.left;
    params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
    tipView.setLayoutParams(params);
    measureViewWithFixedWidth(tipView,params.width);
  }
}
