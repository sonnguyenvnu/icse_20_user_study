private void refreshPalette(){
  if (mPalette != null && mColors != null) {
    mPalette.drawPalette(mColors,mSelectedColor);
  }
}
