/** 
 * Show the content of the PeekView by adding it to the android.R.id.content FrameLayout.
 */
public void show(){
  androidContentView.addView(this);
  content.setTranslationX(distanceFromLeft);
  content.setTranslationY(distanceFromTop);
  ObjectAnimator animator=ObjectAnimator.ofFloat(this,View.ALPHA,0.0f,1.0f);
  animator.addListener(new AnimatorEndListener(){
    @Override public void onAnimationEnd(    Animator animator){
      if (callbacks != null) {
        callbacks.shown();
      }
    }
  }
);
  animator.setDuration(options.useFadeAnimation() ? ANIMATION_TIME : 0);
  animator.setInterpolator(INTERPOLATOR);
  animator.start();
}
