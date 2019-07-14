private void showProgress(final boolean show){
  if (progressAnimation != null) {
    progressAnimation.cancel();
  }
  progressAnimation=new AnimatorSet();
  final View textButton=buttonsLayout.findViewWithTag(BUTTON_POSITIVE);
  if (show) {
    radialProgressView.setVisibility(View.VISIBLE);
    textButton.setEnabled(false);
    progressAnimation.playTogether(ObjectAnimator.ofFloat(textButton,"scaleX",0.1f),ObjectAnimator.ofFloat(textButton,"scaleY",0.1f),ObjectAnimator.ofFloat(textButton,"alpha",0.0f),ObjectAnimator.ofFloat(radialProgressView,"scaleX",1.0f),ObjectAnimator.ofFloat(radialProgressView,"scaleY",1.0f),ObjectAnimator.ofFloat(radialProgressView,"alpha",1.0f));
  }
 else {
    textButton.setVisibility(View.VISIBLE);
    textButton.setEnabled(true);
    progressAnimation.playTogether(ObjectAnimator.ofFloat(radialProgressView,"scaleX",0.1f),ObjectAnimator.ofFloat(radialProgressView,"scaleY",0.1f),ObjectAnimator.ofFloat(radialProgressView,"alpha",0.0f),ObjectAnimator.ofFloat(textButton,"scaleX",1.0f),ObjectAnimator.ofFloat(textButton,"scaleY",1.0f),ObjectAnimator.ofFloat(textButton,"alpha",1.0f));
  }
  progressAnimation.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (progressAnimation != null && progressAnimation.equals(animation)) {
        if (!show) {
          radialProgressView.setVisibility(View.INVISIBLE);
        }
 else {
          textButton.setVisibility(View.INVISIBLE);
        }
      }
    }
    @Override public void onAnimationCancel(    Animator animation){
      if (progressAnimation != null && progressAnimation.equals(animation)) {
        progressAnimation=null;
      }
    }
  }
);
  progressAnimation.setDuration(150);
  progressAnimation.start();
}
