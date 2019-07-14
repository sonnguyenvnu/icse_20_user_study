private static void AdjustRightToOutOfBounds(TextView tipView,ViewGroup root,Point point,RxCoordinates anchorViewRxCoordinates,RxCoordinates rootLocation){
  ViewGroup.LayoutParams params=tipView.getLayoutParams();
  int availableSpace=rootLocation.right - root.getPaddingRight() - anchorViewRxCoordinates.right;
  if (point.x + tipView.getMeasuredWidth() > rootLocation.right - root.getPaddingRight()) {
    params.width=availableSpace;
    params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
    tipView.setLayoutParams(params);
    measureViewWithFixedWidth(tipView,params.width);
  }
}
