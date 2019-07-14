/** 
 * calculate the amount of movement need to be taken inorder to center tip on Y axis
 * @return int
 */
private static int getYCenteringOffset(View tipView,RxPopupView rxPopupView){
  return (rxPopupView.getAnchorView().getHeight() - tipView.getMeasuredHeight()) / 2;
}
