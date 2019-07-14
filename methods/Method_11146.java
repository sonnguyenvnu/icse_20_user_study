private void setTipViewElevation(TextView tipView,RxPopupView rxPopupView){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    if (rxPopupView.getElevation() > 0) {
      ViewOutlineProvider viewOutlineProvider=new ViewOutlineProvider(){
        @SuppressLint("NewApi") @Override public void getOutline(        View view,        Outline outline){
          outline.setEmpty();
        }
      }
;
      tipView.setOutlineProvider(viewOutlineProvider);
      tipView.setElevation(rxPopupView.getElevation());
    }
  }
}
