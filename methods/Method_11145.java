@NonNull private TextView createTipView(RxPopupView rxPopupView){
  TextView tipView=new TextView(rxPopupView.getContext());
  tipView.setTextColor(rxPopupView.getTextColor());
  tipView.setTextSize(rxPopupView.getTextSize());
  tipView.setText(rxPopupView.getMessage() != null ? rxPopupView.getMessage() : rxPopupView.getSpannableMessage());
  tipView.setVisibility(View.INVISIBLE);
  tipView.setGravity(rxPopupView.getTextGravity());
  setTipViewElevation(tipView,rxPopupView);
  return tipView;
}
