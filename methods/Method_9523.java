private void showHint(boolean hide,boolean enabled){
  if (containerView == null || hide && hintTextView == null) {
    return;
  }
  if (hintTextView == null) {
    hintTextView=new TextView(containerView.getContext());
    hintTextView.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(3),0xcc111111));
    hintTextView.setTextColor(0xffffffff);
    hintTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
    hintTextView.setPadding(AndroidUtilities.dp(8),AndroidUtilities.dp(7),AndroidUtilities.dp(8),AndroidUtilities.dp(7));
    hintTextView.setGravity(Gravity.CENTER_VERTICAL);
    hintTextView.setAlpha(0.0f);
    containerView.addView(hintTextView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.LEFT | Gravity.TOP,5,0,5,3));
  }
  if (hide) {
    if (hintAnimation != null) {
      hintAnimation.cancel();
      hintAnimation=null;
    }
    AndroidUtilities.cancelRunOnUIThread(hintHideRunnable);
    hintHideRunnable=null;
    hideHint();
    return;
  }
  hintTextView.setText(enabled ? LocaleController.getString("GroupPhotosHelp",R.string.GroupPhotosHelp) : LocaleController.getString("SinglePhotosHelp",R.string.SinglePhotosHelp));
  if (hintHideRunnable != null) {
    if (hintAnimation != null) {
      hintAnimation.cancel();
      hintAnimation=null;
    }
 else {
      AndroidUtilities.cancelRunOnUIThread(hintHideRunnable);
      AndroidUtilities.runOnUIThread(hintHideRunnable=this::hideHint,2000);
      return;
    }
  }
 else   if (hintAnimation != null) {
    return;
  }
  hintTextView.setVisibility(View.VISIBLE);
  hintAnimation=new AnimatorSet();
  hintAnimation.playTogether(ObjectAnimator.ofFloat(hintTextView,View.ALPHA,1.0f));
  hintAnimation.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (animation.equals(hintAnimation)) {
        hintAnimation=null;
        AndroidUtilities.runOnUIThread(hintHideRunnable=() -> hideHint(),2000);
      }
    }
    @Override public void onAnimationCancel(    Animator animation){
      if (animation.equals(hintAnimation)) {
        hintAnimation=null;
      }
    }
  }
);
  hintAnimation.setDuration(300);
  hintAnimation.start();
}
