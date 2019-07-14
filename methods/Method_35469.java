private void showBubble(){
  if (bubbleView == null)   return;
  bubbleView.setVisibility(VISIBLE);
  if (currentAnimator != null)   currentAnimator.cancel();
  currentAnimator=ObjectAnimator.ofFloat(bubbleView,"alpha",0f,1f).setDuration(BUBBLE_ANIMATION_DURATION);
  currentAnimator.start();
}
