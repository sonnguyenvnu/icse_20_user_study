private static Point getPositionRightTo(TextView tipView,RxPopupView rxPopupView,RxCoordinates anchorViewRxCoordinates,RxCoordinates rootLocation){
  Point point=new Point();
  point.x=anchorViewRxCoordinates.right;
  AdjustRightToOutOfBounds(tipView,rxPopupView.getRootView(),point,anchorViewRxCoordinates,rootLocation);
  point.y=anchorViewRxCoordinates.top + getYCenteringOffset(tipView,rxPopupView);
  return point;
}
