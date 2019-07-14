public boolean showForMessageCell(ChatMessageCell cell,boolean animated){
  if (currentType == 0 && getTag() != null || messageCell == cell) {
    return false;
  }
  if (hideRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(hideRunnable);
    hideRunnable=null;
  }
  int top=cell.getTop();
  int centerX;
  View parentView=(View)cell.getParent();
  if (currentType == 0) {
    ImageReceiver imageReceiver=cell.getPhotoImage();
    top+=imageReceiver.getImageY();
    int height=imageReceiver.getImageHeight();
    int bottom=top + height;
    int parentHeight=parentView.getMeasuredHeight();
    if (top <= getMeasuredHeight() + AndroidUtilities.dp(10) || bottom > parentHeight + height / 4) {
      return false;
    }
    centerX=cell.getNoSoundIconCenterX();
  }
 else {
    MessageObject messageObject=cell.getMessageObject();
    if (overrideText == null) {
      textView.setText(LocaleController.getString("HidAccount",R.string.HidAccount));
    }
 else {
      textView.setText(overrideText);
    }
    measure(MeasureSpec.makeMeasureSpec(1000,MeasureSpec.AT_MOST),MeasureSpec.makeMeasureSpec(1000,MeasureSpec.AT_MOST));
    top+=AndroidUtilities.dp(22);
    if (!messageObject.isOutOwner() && cell.isDrawNameLayout()) {
      top+=AndroidUtilities.dp(20);
    }
    if (!isTopArrow && top <= getMeasuredHeight() + AndroidUtilities.dp(10)) {
      return false;
    }
    centerX=cell.getForwardNameCenterX();
  }
  int parentWidth=parentView.getMeasuredWidth();
  if (isTopArrow) {
    setTranslationY(AndroidUtilities.dp(44));
  }
 else {
    setTranslationY(top - getMeasuredHeight());
  }
  int iconX=cell.getLeft() + centerX;
  int left=AndroidUtilities.dp(19);
  if (iconX > parentView.getMeasuredWidth() / 2) {
    int offset=parentWidth - getMeasuredWidth() - AndroidUtilities.dp(38);
    setTranslationX(offset);
    left+=offset;
  }
 else {
    setTranslationX(0);
  }
  float arrowX=cell.getLeft() + centerX - left - arrowImageView.getMeasuredWidth() / 2;
  arrowImageView.setTranslationX(arrowX);
  if (iconX > parentView.getMeasuredWidth() / 2) {
    if (arrowX < AndroidUtilities.dp(10)) {
      float diff=arrowX - AndroidUtilities.dp(10);
      setTranslationX(getTranslationX() + diff);
      arrowImageView.setTranslationX(arrowX - diff);
    }
  }
 else {
    if (arrowX > getMeasuredWidth() - AndroidUtilities.dp(14 + 10)) {
      float diff=arrowX - getMeasuredWidth() + AndroidUtilities.dp(14 + 10);
      setTranslationX(diff);
      arrowImageView.setTranslationX(arrowX - diff);
    }
 else     if (arrowX < AndroidUtilities.dp(10)) {
      float diff=arrowX - AndroidUtilities.dp(10);
      setTranslationX(getTranslationX() + diff);
      arrowImageView.setTranslationX(arrowX - diff);
    }
  }
  messageCell=cell;
  if (animatorSet != null) {
    animatorSet.cancel();
    animatorSet=null;
  }
  setTag(1);
  setVisibility(VISIBLE);
  if (animated) {
    animatorSet=new AnimatorSet();
    animatorSet.playTogether(ObjectAnimator.ofFloat(this,"alpha",0.0f,1.0f));
    animatorSet.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        animatorSet=null;
        AndroidUtilities.runOnUIThread(hideRunnable=() -> hide(),currentType == 0 ? 10000 : 2000);
      }
    }
);
    animatorSet.setDuration(300);
    animatorSet.start();
  }
 else {
    setAlpha(1.0f);
  }
  return true;
}
