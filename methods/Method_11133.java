private static Point getPositionBelow(TextView tipView,RxPopupView rxPopupView,RxCoordinates anchorViewRxCoordinates,RxCoordinates rootLocation){
  Point point=new Point();
  point.x=anchorViewRxCoordinates.left + getXOffset(tipView,rxPopupView);
  if (rxPopupView.alignedCenter()) {
    AdjustHorizontalCenteredOutOfBounds(tipView,rxPopupView.getRootView(),point,rootLocation);
  }
 else   if (rxPopupView.alignedLeft()) {
    AdjustHorizontalLeftAlignmentOutOfBounds(tipView,rxPopupView.getRootView(),point,anchorViewRxCoordinates,rootLocation);
  }
 else   if (rxPopupView.alignedRight()) {
    AdjustHorizotalRightAlignmentOutOfBounds(tipView,rxPopupView.getRootView(),point,anchorViewRxCoordinates,rootLocation);
  }
  point.y=anchorViewRxCoordinates.bottom;
  return point;
}
