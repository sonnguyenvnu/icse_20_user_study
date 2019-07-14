public void notifyHeightChanged(){
  if (delegate != null) {
    if (parallaxEffect != null) {
      parallaxScale=parallaxEffect.getScale(getMeasuredWidth(),getMeasuredHeight());
    }
    keyboardHeight=getKeyboardHeight();
    final boolean isWidthGreater=AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y;
    post(() -> {
      if (delegate != null) {
        delegate.onSizeChanged(keyboardHeight,isWidthGreater);
      }
    }
);
  }
}
