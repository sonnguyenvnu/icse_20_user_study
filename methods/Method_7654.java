private void startOpenAnimation(){
  if (dismissed) {
    return;
  }
  containerView.setVisibility(View.VISIBLE);
  if (!onCustomOpenAnimation()) {
    if (Build.VERSION.SDK_INT >= 20 && useHardwareLayer) {
      container.setLayerType(View.LAYER_TYPE_HARDWARE,null);
    }
    containerView.setTranslationY(containerView.getMeasuredHeight());
    AnimatorSet animatorSet=new AnimatorSet();
    animatorSet.playTogether(ObjectAnimator.ofFloat(containerView,"translationY",0),ObjectAnimator.ofInt(backDrawable,"alpha",dimBehind ? 51 : 0));
    animatorSet.setDuration(400);
    animatorSet.setStartDelay(20);
    animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    animatorSet.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (currentSheetAnimation != null && currentSheetAnimation.equals(animation)) {
          currentSheetAnimation=null;
          if (delegate != null) {
            delegate.onOpenAnimationEnd();
          }
          if (useHardwareLayer) {
            container.setLayerType(View.LAYER_TYPE_NONE,null);
          }
          if (isFullscreen) {
            WindowManager.LayoutParams params=getWindow().getAttributes();
            params.flags&=~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(params);
          }
        }
      }
      @Override public void onAnimationCancel(      Animator animation){
        if (currentSheetAnimation != null && currentSheetAnimation.equals(animation)) {
          currentSheetAnimation=null;
        }
      }
    }
);
    animatorSet.start();
    currentSheetAnimation=animatorSet;
  }
}
