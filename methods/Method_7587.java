private void checkNeedRebuild(){
  if (rebuildAfterAnimation) {
    rebuildAllFragmentViews(rebuildLastAfterAnimation,showLastAfterAnimation);
    rebuildAfterAnimation=false;
  }
 else   if (animateThemeAfterAnimation) {
    animateThemedValues(animateSetThemeAfterAnimation,animateSetThemeNightAfterAnimation);
    animateSetThemeAfterAnimation=null;
    animateThemeAfterAnimation=false;
  }
}
