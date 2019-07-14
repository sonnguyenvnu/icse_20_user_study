private void setReferrerWidth(final Float percent,final View bar,final View indicator){
  final ConstraintLayout.LayoutParams barLayoutParams=(ConstraintLayout.LayoutParams)bar.getLayoutParams();
  barLayoutParams.horizontalWeight=percent;
  bar.setLayoutParams(barLayoutParams);
  adjustIndicatorMarginForShortBar(bar,indicator);
}
