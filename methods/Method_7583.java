public void animateThemedValues(Theme.ThemeInfo theme,boolean nightTheme){
  if (transitionAnimationInProgress || startedTracking) {
    animateThemeAfterAnimation=true;
    animateSetThemeAfterAnimation=theme;
    animateSetThemeNightAfterAnimation=nightTheme;
    return;
  }
  if (themeAnimatorSet != null) {
    themeAnimatorSet.cancel();
    themeAnimatorSet=null;
  }
  boolean startAnimation=false;
  for (int i=0; i < 2; i++) {
    BaseFragment fragment;
    if (i == 0) {
      fragment=getLastFragment();
    }
 else {
      if (!inPreviewMode && !transitionAnimationPreviewMode || fragmentsStack.size() <= 1) {
        themeAnimatorDescriptions[i]=null;
        animateStartColors[i]=null;
        animateEndColors[i]=null;
        themeAnimatorDelegate[i]=null;
        continue;
      }
      fragment=fragmentsStack.get(fragmentsStack.size() - 2);
    }
    if (fragment != null) {
      startAnimation=true;
      themeAnimatorDescriptions[i]=fragment.getThemeDescriptions();
      animateStartColors[i]=new int[themeAnimatorDescriptions[i].length];
      for (int a=0; a < themeAnimatorDescriptions[i].length; a++) {
        animateStartColors[i][a]=themeAnimatorDescriptions[i][a].getSetColor();
        ThemeDescription.ThemeDescriptionDelegate delegate=themeAnimatorDescriptions[i][a].setDelegateDisabled();
        if (themeAnimatorDelegate[i] == null && delegate != null) {
          themeAnimatorDelegate[i]=delegate;
        }
      }
      if (i == 0) {
        Theme.applyTheme(theme,nightTheme);
      }
      animateEndColors[i]=new int[themeAnimatorDescriptions[i].length];
      for (int a=0; a < themeAnimatorDescriptions[i].length; a++) {
        animateEndColors[i][a]=themeAnimatorDescriptions[i][a].getSetColor();
      }
    }
  }
  if (startAnimation) {
    themeAnimatorSet=new AnimatorSet();
    themeAnimatorSet.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (animation.equals(themeAnimatorSet)) {
          for (int a=0; a < 2; a++) {
            themeAnimatorDescriptions[a]=null;
            animateStartColors[a]=null;
            animateEndColors[a]=null;
            themeAnimatorDelegate[a]=null;
          }
          Theme.setAnimatingColor(false);
          themeAnimatorSet=null;
        }
      }
      @Override public void onAnimationCancel(      Animator animation){
        if (animation.equals(themeAnimatorSet)) {
          for (int a=0; a < 2; a++) {
            themeAnimatorDescriptions[a]=null;
            animateStartColors[a]=null;
            animateEndColors[a]=null;
            themeAnimatorDelegate[a]=null;
          }
          Theme.setAnimatingColor(false);
          themeAnimatorSet=null;
        }
      }
    }
);
    int count=fragmentsStack.size() - (inPreviewMode || transitionAnimationPreviewMode ? 2 : 1);
    for (int a=0; a < count; a++) {
      BaseFragment fragment=fragmentsStack.get(a);
      fragment.clearViews();
      fragment.setParentLayout(this);
    }
    Theme.setAnimatingColor(true);
    themeAnimatorSet.playTogether(ObjectAnimator.ofFloat(this,"themeAnimationValue",0.0f,1.0f));
    themeAnimatorSet.setDuration(200);
    themeAnimatorSet.start();
  }
}
