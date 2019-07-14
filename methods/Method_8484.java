private void checkStickresExpandHeight(){
  final int origHeight=AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y ? keyboardHeightLand : keyboardHeight;
  int newHeight=originalViewHeight - (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? AndroidUtilities.statusBarHeight : 0) - ActionBar.getCurrentActionBarHeight() - getHeight() + Theme.chat_composeShadowDrawable.getIntrinsicHeight();
  if (searchingType == 2) {
    newHeight=Math.min(newHeight,AndroidUtilities.dp(120) + origHeight);
  }
  int currentHeight=emojiView.getLayoutParams().height;
  if (currentHeight == newHeight) {
    return;
  }
  if (stickersExpansionAnim != null) {
    stickersExpansionAnim.cancel();
    stickersExpansionAnim=null;
  }
  stickersExpandedHeight=newHeight;
  if (currentHeight > newHeight) {
    AnimatorSet anims=new AnimatorSet();
    anims.playTogether(ObjectAnimator.ofInt(this,roundedTranslationYProperty,-(stickersExpandedHeight - origHeight)),ObjectAnimator.ofInt(emojiView,roundedTranslationYProperty,-(stickersExpandedHeight - origHeight)));
    ((ObjectAnimator)anims.getChildAnimations().get(0)).addUpdateListener(animation -> sizeNotifierLayout.invalidate());
    anims.setDuration(400);
    anims.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    anims.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        stickersExpansionAnim=null;
        if (emojiView != null) {
          emojiView.getLayoutParams().height=stickersExpandedHeight;
          emojiView.setLayerType(LAYER_TYPE_NONE,null);
        }
      }
    }
);
    stickersExpansionAnim=anims;
    emojiView.setLayerType(LAYER_TYPE_HARDWARE,null);
    anims.start();
  }
 else {
    emojiView.getLayoutParams().height=stickersExpandedHeight;
    sizeNotifierLayout.requestLayout();
    int start=messageEditText.getSelectionStart();
    int end=messageEditText.getSelectionEnd();
    messageEditText.setText(messageEditText.getText());
    messageEditText.setSelection(start,end);
    AnimatorSet anims=new AnimatorSet();
    anims.playTogether(ObjectAnimator.ofInt(this,roundedTranslationYProperty,-(stickersExpandedHeight - origHeight)),ObjectAnimator.ofInt(emojiView,roundedTranslationYProperty,-(stickersExpandedHeight - origHeight)));
    ((ObjectAnimator)anims.getChildAnimations().get(0)).addUpdateListener(animation -> sizeNotifierLayout.invalidate());
    anims.setDuration(400);
    anims.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    anims.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        stickersExpansionAnim=null;
        emojiView.setLayerType(LAYER_TYPE_NONE,null);
      }
    }
);
    stickersExpansionAnim=anims;
    emojiView.setLayerType(LAYER_TYPE_HARDWARE,null);
    anims.start();
  }
}
