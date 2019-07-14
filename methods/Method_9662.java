private void setEmojiTooltipVisible(boolean visible){
  emojiTooltipVisible=visible;
  if (tooltipAnim != null)   tooltipAnim.cancel();
  hintTextView.setVisibility(View.VISIBLE);
  ObjectAnimator oa=ObjectAnimator.ofFloat(hintTextView,"alpha",visible ? 1 : 0);
  oa.setDuration(300);
  oa.setInterpolator(CubicBezierInterpolator.DEFAULT);
  oa.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      tooltipAnim=null;
    }
  }
);
  tooltipAnim=oa;
  oa.start();
}
