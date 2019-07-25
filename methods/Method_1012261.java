/** 
 * Hides GuideCaseView with animation
 */
public void hide(){
  if (mExitAnimation != null) {
    startAnimation(mExitAnimation);
  }
 else   if (Utils.shouldShowCircularAnimation()) {
    doCircularExitAnimation();
  }
 else {
    Animation fadeOut=AnimationUtils.loadAnimation(mActivity,R.anim.gcv_fade_out);
    fadeOut.setAnimationListener(new Animation.AnimationListener(){
      @Override public void onAnimationStart(      Animation animation){
      }
      @Override public void onAnimationEnd(      Animation animation){
        removeView();
      }
      @Override public void onAnimationRepeat(      Animation animation){
      }
    }
);
    fadeOut.setFillAfter(true);
    startAnimation(fadeOut);
  }
}
