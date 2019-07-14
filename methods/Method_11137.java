private static void AdjustHorizotalRightAlignmentOutOfBounds(TextView tipView,ViewGroup root,Point point,RxCoordinates anchorViewRxCoordinates,RxCoordinates rootLocation){
  ViewGroup.LayoutParams params=tipView.getLayoutParams();
  int rootLeft=rootLocation.left + root.getPaddingLeft();
  if (point.x < rootLeft) {
    int availableSpace=anchorViewRxCoordinates.right - rootLeft;
    point.x=rootLeft;
    params.width=availableSpace;
    params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
    tipView.setLayoutParams(params);
    measureViewWithFixedWidth(tipView,params.width);
  }
}
