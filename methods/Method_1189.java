@Override public boolean setVisible(boolean visible,boolean restart){
  if (mVisibilityCallback != null) {
    mVisibilityCallback.onVisibilityChange(visible);
  }
  return super.setVisible(visible,restart);
}
