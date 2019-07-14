private void animateMotionChange(){
  if (motionAnimation != null) {
    motionAnimation.cancel();
  }
  motionAnimation=new AnimatorSet();
  if (isMotion) {
    motionAnimation.playTogether(ObjectAnimator.ofFloat(backgroundImage,View.SCALE_X,parallaxScale),ObjectAnimator.ofFloat(backgroundImage,View.SCALE_Y,parallaxScale));
  }
 else {
    motionAnimation.playTogether(ObjectAnimator.ofFloat(backgroundImage,View.SCALE_X,1.0f),ObjectAnimator.ofFloat(backgroundImage,View.SCALE_Y,1.0f),ObjectAnimator.ofFloat(backgroundImage,View.TRANSLATION_X,0.0f),ObjectAnimator.ofFloat(backgroundImage,View.TRANSLATION_Y,0.0f));
  }
  motionAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
  motionAnimation.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      motionAnimation=null;
    }
  }
);
  motionAnimation.start();
}
