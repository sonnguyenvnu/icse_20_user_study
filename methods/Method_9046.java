public void updateSelectedCount(int animated){
  if (selectedDialogs.size() == 0) {
    selectedCountView.setPivotX(0);
    selectedCountView.setPivotY(0);
    showCommentTextView(false);
  }
 else {
    selectedCountView.invalidate();
    if (animated != 0 && !showCommentTextView(true)) {
      selectedCountView.setPivotX(AndroidUtilities.dp(21));
      selectedCountView.setPivotY(AndroidUtilities.dp(12));
      AnimatorSet animatorSet=new AnimatorSet();
      animatorSet.playTogether(ObjectAnimator.ofFloat(selectedCountView,View.SCALE_X,animated == 1 ? 1.1f : 0.9f,1.0f),ObjectAnimator.ofFloat(selectedCountView,View.SCALE_Y,animated == 1 ? 1.1f : 0.9f,1.0f));
      animatorSet.setInterpolator(new OvershootInterpolator());
      animatorSet.setDuration(180);
      animatorSet.start();
    }
 else {
      selectedCountView.setPivotX(0);
      selectedCountView.setPivotY(0);
    }
  }
}
