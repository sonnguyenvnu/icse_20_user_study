public void dismissWithButtonClick(final int item){
  if (dismissed) {
    return;
  }
  dismissed=true;
  cancelSheetAnimation();
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.playTogether(ObjectAnimator.ofFloat(containerView,"translationY",containerView.getMeasuredHeight() + AndroidUtilities.dp(10)),ObjectAnimator.ofInt(backDrawable,"alpha",0));
  animatorSet.setDuration(180);
  animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT);
  animatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (currentSheetAnimation != null && currentSheetAnimation.equals(animation)) {
        currentSheetAnimation=null;
        if (onClickListener != null) {
          onClickListener.onClick(BottomSheet.this,item);
        }
        AndroidUtilities.runOnUIThread(() -> {
          try {
            BottomSheet.super.dismiss();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
);
      }
    }
    @Override public void onAnimationCancel(    Animator animation){
      if (currentSheetAnimation != null && currentSheetAnimation.equals(animation)) {
        currentSheetAnimation=null;
      }
    }
  }
);
  animatorSet.start();
  currentSheetAnimation=animatorSet;
}
