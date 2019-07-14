private void showRetry(){
  if (retryAnim != null)   retryAnim.cancel();
  endBtn.setEnabled(false);
  retrying=true;
  cancelBtn.setVisibility(View.VISIBLE);
  cancelBtn.setAlpha(0);
  AnimatorSet set=new AnimatorSet();
  ObjectAnimator colorAnim;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    colorAnim=ObjectAnimator.ofArgb(endBtnBg,"color",0xFFe61e44,0xFF45bc4d);
  }
 else {
    colorAnim=ObjectAnimator.ofInt(endBtnBg,"color",0xFFe61e44,0xFF45bc4d);
    colorAnim.setEvaluator(new ArgbEvaluator());
  }
  set.playTogether(ObjectAnimator.ofFloat(cancelBtn,"alpha",0,1),ObjectAnimator.ofFloat(endBtn,"translationX",0,content.getWidth() / 2 - AndroidUtilities.dp(52) - endBtn.getWidth() / 2),colorAnim,ObjectAnimator.ofFloat(endBtnIcon,"rotation",0,-135));
  set.setStartDelay(200);
  set.setDuration(300);
  set.setInterpolator(CubicBezierInterpolator.DEFAULT);
  set.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      retryAnim=null;
      endBtn.setEnabled(true);
    }
  }
);
  retryAnim=set;
  set.start();
}
