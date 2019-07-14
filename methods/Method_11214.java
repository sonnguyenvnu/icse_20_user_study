private void drawTextProgressMargin(){
  ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams)tvProgress.getLayoutParams();
  params.setMargins(textProgressMargin,0,textProgressMargin,0);
  tvProgress.setLayoutParams(params);
}
