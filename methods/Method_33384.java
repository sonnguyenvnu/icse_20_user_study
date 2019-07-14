public void layoutPane(final double x,final double y,final double w,final double h){
  relocate(x,y);
  if (control.isDisableAnimation() || isErrorVisible()) {
    resize(w,computeErrorHeight(computeErrorWidth(w)));
    errorContainerClip.setWidth(w);
  }
}
