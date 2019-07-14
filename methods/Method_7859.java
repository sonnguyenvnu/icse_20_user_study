public void uncollapse(){
  if (parentActivity == null || !isVisible || checkAnimation()) {
    return;
  }
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.playTogether(ObjectAnimator.ofFloat(containerView,View.TRANSLATION_X,0),ObjectAnimator.ofFloat(containerView,View.TRANSLATION_Y,0),ObjectAnimator.ofFloat(windowView,View.ALPHA,1.0f),ObjectAnimator.ofFloat(listView[0],View.ALPHA,1.0f),ObjectAnimator.ofFloat(listView[0],View.TRANSLATION_Y,0),ObjectAnimator.ofFloat(headerView,View.TRANSLATION_Y,0),ObjectAnimator.ofFloat(backButton,View.SCALE_X,1.0f),ObjectAnimator.ofFloat(backButton,View.SCALE_Y,1.0f),ObjectAnimator.ofFloat(backButton,View.TRANSLATION_Y,0),ObjectAnimator.ofFloat(shareContainer,View.SCALE_X,1.0f),ObjectAnimator.ofFloat(shareContainer,View.TRANSLATION_Y,0),ObjectAnimator.ofFloat(shareContainer,View.SCALE_Y,1.0f));
  collapsed=false;
  animationInProgress=2;
  animationEndRunnable=() -> {
    if (containerView == null) {
      return;
    }
    if (Build.VERSION.SDK_INT >= 18) {
      containerView.setLayerType(View.LAYER_TYPE_NONE,null);
    }
    animationInProgress=0;
  }
;
  animatorSet.setDuration(250);
  animatorSet.setInterpolator(new DecelerateInterpolator());
  animatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (animationEndRunnable != null) {
        animationEndRunnable.run();
        animationEndRunnable=null;
      }
    }
  }
);
  transitionAnimationStartTime=System.currentTimeMillis();
  if (Build.VERSION.SDK_INT >= 18) {
    containerView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
  }
  backDrawable.setRotation(0,true);
  animatorSet.start();
}
