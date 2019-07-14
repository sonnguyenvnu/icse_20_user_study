private void showBackspaceButton(boolean show,boolean animated){
  if (show && backspaceButton.getTag() == null || !show && backspaceButton.getTag() != null) {
    return;
  }
  if (backspaceButtonAnimation != null) {
    backspaceButtonAnimation.cancel();
    backspaceButtonAnimation=null;
  }
  backspaceButton.setTag(show ? null : 1);
  if (animated) {
    if (show) {
      backspaceButton.setVisibility(VISIBLE);
    }
    backspaceButtonAnimation=new AnimatorSet();
    backspaceButtonAnimation.playTogether(ObjectAnimator.ofFloat(backspaceButton,View.ALPHA,show ? 1.0f : 0.0f),ObjectAnimator.ofFloat(backspaceButton,View.SCALE_X,show ? 1.0f : 0.0f),ObjectAnimator.ofFloat(backspaceButton,View.SCALE_Y,show ? 1.0f : 0.0f));
    backspaceButtonAnimation.setDuration(200);
    backspaceButtonAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
    backspaceButtonAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (!show) {
          backspaceButton.setVisibility(INVISIBLE);
        }
      }
    }
);
    backspaceButtonAnimation.start();
  }
 else {
    backspaceButton.setAlpha(show ? 1.0f : 0.0f);
    backspaceButton.setScaleX(show ? 1.0f : 0.0f);
    backspaceButton.setScaleY(show ? 1.0f : 0.0f);
    backspaceButton.setVisibility(show ? VISIBLE : INVISIBLE);
  }
}
