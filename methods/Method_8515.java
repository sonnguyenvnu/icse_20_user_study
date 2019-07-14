@Override protected boolean onCustomCloseAnimation(){
  if (useRevealAnimation) {
    setUseRevealAnimation(true);
  }
  if (useRevealAnimation) {
    backDrawable.setAlpha(51);
    startRevealAnimation(false);
    return true;
  }
  return false;
}
