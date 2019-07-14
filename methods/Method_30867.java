private void setToolbarAlpha(int alpha){
  if (mAlpha == alpha) {
    return;
  }
  mAlpha=alpha;
  setTitleTextColor(ColorUtils.blendAlphaComponent(mTitleTextColor,mAlpha));
  getBackground().setAlpha(mAlpha);
}
