public void startAnimation(){
  if (animationEnabled) {
    if (windowAnimatorSet != null) {
      return;
    }
    ActionBarPopupWindowLayout content=(ActionBarPopupWindowLayout)getContentView();
    content.setTranslationY(0);
    content.setAlpha(1.0f);
    content.setPivotX(content.getMeasuredWidth());
    content.setPivotY(0);
    int count=content.getItemsCount();
    content.positions.clear();
    int visibleCount=0;
    for (int a=0; a < count; a++) {
      View child=content.getItemAt(a);
      if (child.getVisibility() != View.VISIBLE) {
        continue;
      }
      content.positions.put(child,visibleCount);
      child.setAlpha(0.0f);
      visibleCount++;
    }
    if (content.showedFromBotton) {
      content.lastStartedChild=count - 1;
    }
 else {
      content.lastStartedChild=0;
    }
    windowAnimatorSet=new AnimatorSet();
    windowAnimatorSet.playTogether(ObjectAnimator.ofFloat(content,"backScaleY",0.0f,1.0f),ObjectAnimator.ofInt(content,"backAlpha",0,255));
    windowAnimatorSet.setDuration(150 + 16 * visibleCount);
    windowAnimatorSet.addListener(new Animator.AnimatorListener(){
      @Override public void onAnimationStart(      Animator animation){
      }
      @Override public void onAnimationEnd(      Animator animation){
        windowAnimatorSet=null;
      }
      @Override public void onAnimationCancel(      Animator animation){
        onAnimationEnd(animation);
      }
      @Override public void onAnimationRepeat(      Animator animation){
      }
    }
);
    windowAnimatorSet.start();
  }
}
