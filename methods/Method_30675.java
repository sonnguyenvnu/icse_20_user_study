public void offsetTo(int offset){
  if (offset < 0) {
    offset=0;
  }
  if (getOffset() == offset) {
    return;
  }
  mOffsetContainer.setTranslationY(offset);
  updateStatusBarAndWindowBackground(offset);
}
