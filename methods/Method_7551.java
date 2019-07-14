public void showActionMode(View extraView,View showingView,View[] hidingViews,boolean[] hideView,View translationView,int translation){
  if (actionMode == null || actionModeVisible) {
    return;
  }
  actionModeVisible=true;
  ArrayList<Animator> animators=new ArrayList<>();
  animators.add(ObjectAnimator.ofFloat(actionMode,View.ALPHA,0.0f,1.0f));
  if (hidingViews != null) {
    for (int a=0; a < hidingViews.length; a++) {
      if (hidingViews[a] != null) {
        animators.add(ObjectAnimator.ofFloat(hidingViews[a],View.ALPHA,1.0f,0.0f));
      }
    }
  }
  if (showingView != null) {
    animators.add(ObjectAnimator.ofFloat(showingView,View.ALPHA,0.0f,1.0f));
  }
  if (translationView != null) {
    animators.add(ObjectAnimator.ofFloat(translationView,View.TRANSLATION_Y,translation));
    actionModeTranslationView=translationView;
  }
  actionModeExtraView=extraView;
  actionModeShowingView=showingView;
  actionModeHidingViews=hidingViews;
  if (occupyStatusBar && actionModeTop != null) {
    animators.add(ObjectAnimator.ofFloat(actionModeTop,View.ALPHA,0.0f,1.0f));
  }
  if (actionModeAnimation != null) {
    actionModeAnimation.cancel();
  }
  actionModeAnimation=new AnimatorSet();
  actionModeAnimation.playTogether(animators);
  actionModeAnimation.setDuration(200);
  actionModeAnimation.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationStart(    Animator animation){
      actionMode.setVisibility(VISIBLE);
      if (occupyStatusBar && actionModeTop != null) {
        actionModeTop.setVisibility(VISIBLE);
      }
    }
    @Override public void onAnimationEnd(    Animator animation){
      if (actionModeAnimation != null && actionModeAnimation.equals(animation)) {
        actionModeAnimation=null;
        if (titleTextView != null) {
          titleTextView.setVisibility(INVISIBLE);
        }
        if (subtitleTextView != null && !TextUtils.isEmpty(subtitleTextView.getText())) {
          subtitleTextView.setVisibility(INVISIBLE);
        }
        if (menu != null) {
          menu.setVisibility(INVISIBLE);
        }
        if (actionModeHidingViews != null) {
          for (int a=0; a < actionModeHidingViews.length; a++) {
            if (actionModeHidingViews[a] != null) {
              if (hideView == null || a >= hideView.length || hideView[a]) {
                actionModeHidingViews[a].setVisibility(INVISIBLE);
              }
            }
          }
        }
      }
    }
    @Override public void onAnimationCancel(    Animator animation){
      if (actionModeAnimation != null && actionModeAnimation.equals(animation)) {
        actionModeAnimation=null;
      }
    }
  }
);
  actionModeAnimation.start();
  if (backButtonImageView != null) {
    Drawable drawable=backButtonImageView.getDrawable();
    if (drawable instanceof BackDrawable) {
      ((BackDrawable)drawable).setRotation(1,true);
    }
    backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(itemsActionModeBackgroundColor));
  }
}
