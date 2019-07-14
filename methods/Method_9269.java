private void updateEmojiButton(boolean animated){
  if (animatorSet != null) {
    animatorSet.cancel();
    animatorSet=null;
  }
  if (animated) {
    animatorSet=new AnimatorSet();
    animatorSet.playTogether(ObjectAnimator.ofFloat(emojiTextView,"alpha",emojiSelected ? 1.0f : 0.0f),ObjectAnimator.ofFloat(codeTextView,"alpha",emojiSelected ? 0.0f : 1.0f),ObjectAnimator.ofFloat(emojiTextView,"scaleX",emojiSelected ? 1.0f : 0.0f),ObjectAnimator.ofFloat(emojiTextView,"scaleY",emojiSelected ? 1.0f : 0.0f),ObjectAnimator.ofFloat(codeTextView,"scaleX",emojiSelected ? 0.0f : 1.0f),ObjectAnimator.ofFloat(codeTextView,"scaleY",emojiSelected ? 0.0f : 1.0f));
    animatorSet.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (animation.equals(animatorSet)) {
          animatorSet=null;
        }
      }
    }
);
    animatorSet.setInterpolator(new DecelerateInterpolator());
    animatorSet.setDuration(150);
    animatorSet.start();
  }
 else {
    emojiTextView.setAlpha(emojiSelected ? 1.0f : 0.0f);
    codeTextView.setAlpha(emojiSelected ? 0.0f : 1.0f);
    emojiTextView.setScaleX(emojiSelected ? 1.0f : 0.0f);
    emojiTextView.setScaleY(emojiSelected ? 1.0f : 0.0f);
    codeTextView.setScaleX(emojiSelected ? 0.0f : 1.0f);
    codeTextView.setScaleY(emojiSelected ? 0.0f : 1.0f);
  }
  emojiTextView.setTag(!emojiSelected ? Theme.key_chat_emojiPanelIcon : Theme.key_chat_emojiPanelIconSelected);
}
