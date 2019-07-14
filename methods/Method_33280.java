@Override public void layoutChildren(){
  super.layoutChildren();
  if (dialog.getMinWidth() > 0 && dialog.getMinHeight() > 0) {
    return;
  }
  double minWidth=Math.max(0,computeMinWidth(getHeight()) + (dialog.getWidth() - customScene.getWidth()));
  double minHeight=Math.max(0,computeMinHeight(getWidth()) + (dialog.getHeight() - customScene.getHeight()));
  dialog.setMinWidth(minWidth);
  dialog.setMinHeight(minHeight);
}
