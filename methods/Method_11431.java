@Override protected void onScrollTouched(){
  mDimSelectorWheelAnimator.cancel();
  mDimSeparatorsAnimator.cancel();
  setSelectorPaintCoeff(1);
  setSeparatorsPaintAlpha(mSelectionDividerActiveAlpha);
}
