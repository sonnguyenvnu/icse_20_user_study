private void hideFloatingButton(boolean hide){
  if (floatingHidden == hide) {
    return;
  }
  floatingHidden=hide;
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.playTogether(ObjectAnimator.ofFloat(floatingButtonContainer,View.TRANSLATION_Y,(floatingHidden ? AndroidUtilities.dp(100) : -additionalFloatingTranslation)));
  animatorSet.setDuration(300);
  animatorSet.setInterpolator(floatingInterpolator);
  floatingButtonContainer.setClickable(!hide);
  animatorSet.start();
}
