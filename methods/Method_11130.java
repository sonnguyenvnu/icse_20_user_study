/** 
 * return the top left coordinates for positioning the tip
 * @param tipView - the newly created tip view
 * @param popupView - tool tip object
 * @return point
 */
static Point getCoordinates(final TextView tipView,RxPopupView popupView){
  Point point=new Point();
  final RxCoordinates anchorViewRxCoordinates=new RxCoordinates(popupView.getAnchorView());
  final RxCoordinates rootRxCoordinates=new RxCoordinates(popupView.getRootView());
  tipView.measure(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
switch (popupView.getPosition()) {
case RxPopupView.POSITION_ABOVE:
    point=getPositionAbove(tipView,popupView,anchorViewRxCoordinates,rootRxCoordinates);
  break;
case RxPopupView.POSITION_BELOW:
point=getPositionBelow(tipView,popupView,anchorViewRxCoordinates,rootRxCoordinates);
break;
case RxPopupView.POSITION_LEFT_TO:
point=getPositionLeftTo(tipView,popupView,anchorViewRxCoordinates,rootRxCoordinates);
break;
case RxPopupView.POSITION_RIGHT_TO:
point=getPositionRightTo(tipView,popupView,anchorViewRxCoordinates,rootRxCoordinates);
break;
default :
break;
}
point.x+=RxPopupViewTool.isRtl() ? -popupView.getOffsetX() : popupView.getOffsetX();
point.y+=popupView.getOffsetY();
point.x-=popupView.getRootView().getPaddingLeft();
point.y-=popupView.getRootView().getPaddingTop();
return point;
}
