private void setDrawableVisibilitySafe(boolean visible,boolean restart){
  if (mDrawable != null && mDrawable.isVisible() != visible) {
    try {
      mDrawable.setVisible(visible,restart);
    }
 catch (    NullPointerException e) {
    }
  }
}
