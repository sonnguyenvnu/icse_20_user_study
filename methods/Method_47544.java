public void showPaletteView(){
  if (mProgress != null && mPalette != null) {
    mProgress.setVisibility(View.GONE);
    refreshPalette();
    mPalette.setVisibility(View.VISIBLE);
  }
}
