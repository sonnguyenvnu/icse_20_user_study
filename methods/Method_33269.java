@Override protected void handleControlPropertyChanged(String propertyReference){
  if ("DISABLE_NODE".equals(propertyReference)) {
    linesWrapper.updateDisabled();
  }
 else   if ("FOCUS_COLOR".equals(propertyReference)) {
    linesWrapper.updateFocusColor();
  }
 else   if ("UNFOCUS_COLOR".equals(propertyReference)) {
    linesWrapper.updateUnfocusColor();
  }
 else   if ("DISABLE_ANIMATION".equals(propertyReference)) {
    errorContainer.updateClip();
  }
 else {
    super.handleControlPropertyChanged(propertyReference);
  }
}
