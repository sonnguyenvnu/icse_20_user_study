private void switchToolTipSidePosition(RxPopupView rxPopupView){
  if (rxPopupView.positionedLeftTo()) {
    rxPopupView.setPosition(RxPopupView.POSITION_RIGHT_TO);
  }
 else   if (rxPopupView.positionedRightTo()) {
    rxPopupView.setPosition(RxPopupView.POSITION_LEFT_TO);
  }
}
