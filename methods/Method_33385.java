public void invalid(double w){
  final ValidatorBase activeValidator=control.getActiveValidator();
  if (activeValidator != null) {
    showError(activeValidator);
    final double errorContainerWidth=w - errorIcon.prefWidth(-1);
    setOpacity(1);
    resize(w,computeErrorHeight(errorContainerWidth));
    errorContainerClip.setWidth(w);
    errorContainerClip.setHeight(getHeight());
    errorClipScale.setY(1);
  }
}
