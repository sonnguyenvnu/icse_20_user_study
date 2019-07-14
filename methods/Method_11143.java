private View create(RxPopupView rxPopupView){
  if (rxPopupView.getAnchorView() == null) {
    Log.e(TAG,"Unable to create a tip, anchor view is null");
    return null;
  }
  if (rxPopupView.getRootView() == null) {
    Log.e(TAG,"Unable to create a tip, root layout is null");
    return null;
  }
  if (mTipsMap.containsKey(rxPopupView.getAnchorView().getId())) {
    return mTipsMap.get(rxPopupView.getAnchorView().getId());
  }
  TextView tipView=createTipView(rxPopupView);
  if (RxPopupViewTool.isRtl()) {
    switchToolTipSidePosition(rxPopupView);
  }
  RxPopupViewBackgroundConstructor.setBackground(tipView,rxPopupView);
  rxPopupView.getRootView().addView(tipView);
  Point p=RxPopupViewCoordinatesFinder.getCoordinates(tipView,rxPopupView);
  moveTipToCorrectPosition(tipView,p);
  tipView.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View view){
      dismiss(view,true);
    }
  }
);
  int anchorViewId=rxPopupView.getAnchorView().getId();
  tipView.setTag(anchorViewId);
  mTipsMap.put(anchorViewId,tipView);
  return tipView;
}
