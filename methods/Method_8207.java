private void showAvatarProgress(boolean show,boolean animated){
  if (avatarEditor == null) {
    return;
  }
  if (avatarAnimation != null) {
    avatarAnimation.cancel();
    avatarAnimation=null;
  }
  if (animated) {
    avatarAnimation=new AnimatorSet();
    if (show) {
      avatarProgressView.setVisibility(View.VISIBLE);
      avatarAnimation.playTogether(ObjectAnimator.ofFloat(avatarEditor,View.ALPHA,0.0f),ObjectAnimator.ofFloat(avatarProgressView,View.ALPHA,1.0f));
    }
 else {
      avatarEditor.setVisibility(View.VISIBLE);
      avatarAnimation.playTogether(ObjectAnimator.ofFloat(avatarEditor,View.ALPHA,1.0f),ObjectAnimator.ofFloat(avatarProgressView,View.ALPHA,0.0f));
    }
    avatarAnimation.setDuration(180);
    avatarAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (avatarAnimation == null || avatarEditor == null) {
          return;
        }
        if (show) {
          avatarEditor.setVisibility(View.INVISIBLE);
        }
 else {
          avatarProgressView.setVisibility(View.INVISIBLE);
        }
        avatarAnimation=null;
      }
      @Override public void onAnimationCancel(      Animator animation){
        avatarAnimation=null;
      }
    }
);
    avatarAnimation.start();
  }
 else {
    if (show) {
      avatarEditor.setAlpha(1.0f);
      avatarEditor.setVisibility(View.INVISIBLE);
      avatarProgressView.setAlpha(1.0f);
      avatarProgressView.setVisibility(View.VISIBLE);
    }
 else {
      avatarEditor.setAlpha(1.0f);
      avatarEditor.setVisibility(View.VISIBLE);
      avatarProgressView.setAlpha(0.0f);
      avatarProgressView.setVisibility(View.INVISIBLE);
    }
  }
}
