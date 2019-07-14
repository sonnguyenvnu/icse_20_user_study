public void setDrawablePadding(int value){
  if (drawablePadding == value) {
    return;
  }
  drawablePadding=value;
  if (!recreateLayoutMaybe()) {
    invalidate();
  }
}
