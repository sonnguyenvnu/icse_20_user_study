public void start(final TitanicTextView textView){
  final Runnable animate=new Runnable(){
    @Override public void run(){
      textView.setSinking(true);
      ObjectAnimator maskXAnimator=ObjectAnimator.ofFloat(textView,"maskX",0,200);
      maskXAnimator.setRepeatCount(ValueAnimator.INFINITE);
      maskXAnimator.setDuration(1000);
      maskXAnimator.setStartDelay(0);
      int h=textView.getHeight();
      ObjectAnimator maskYAnimator=ObjectAnimator.ofFloat(textView,"maskY",h / 2,-h / 2);
      maskYAnimator.setRepeatCount(ValueAnimator.INFINITE);
      maskYAnimator.setRepeatMode(ValueAnimator.REVERSE);
      maskYAnimator.setDuration(10000);
      maskYAnimator.setStartDelay(0);
      animatorSet=new AnimatorSet();
      animatorSet.playTogether(maskXAnimator,maskYAnimator);
      animatorSet.setInterpolator(new LinearInterpolator());
      animatorSet.addListener(new Animator.AnimatorListener(){
        @Override public void onAnimationStart(        Animator animation){
        }
        @Override public void onAnimationEnd(        Animator animation){
          textView.setSinking(false);
          if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            textView.postInvalidate();
          }
 else {
            textView.postInvalidateOnAnimation();
          }
          animatorSet=null;
        }
        @Override public void onAnimationCancel(        Animator animation){
        }
        @Override public void onAnimationRepeat(        Animator animation){
        }
      }
);
      if (animatorListener != null) {
        animatorSet.addListener(animatorListener);
      }
      animatorSet.start();
    }
  }
;
  if (!textView.isSetUp()) {
    textView.setAnimationSetupCallback(new TitanicTextView.AnimationSetupCallback(){
      @Override public void onSetupAnimation(      final TitanicTextView target){
        animate.run();
      }
    }
);
  }
 else {
    animate.run();
  }
}
