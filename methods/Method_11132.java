private static Point getPositionLeftTo(TextView tipView,RxPopupView rxPopupView,RxCoordinates anchorViewRxCoordinates,RxCoordinates rootLocation){
  Point point=new Point();
  point.x=anchorViewRxCoordinates.left - tipView.getMeasuredWidth();
  AdjustLeftToOutOfBounds(tipView,rxPopupView.getRootView(),point,anchorViewRxCoordinates,rootLocation);
  point.y=anchorViewRxCoordinates.top + getYCenteringOffset(tipView,rxPopupView);
  return point;
}
