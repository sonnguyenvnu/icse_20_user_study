public void movePreviewFragment(float dy){
  if (!inPreviewMode || transitionAnimationPreviewMode) {
    return;
  }
  float currentTranslation=containerView.getTranslationY();
  float nextTranslation=-dy;
  if (nextTranslation > 0) {
    nextTranslation=0;
  }
 else   if (nextTranslation < -AndroidUtilities.dp(60)) {
    inPreviewMode=false;
    nextTranslation=0;
    BaseFragment prevFragment=fragmentsStack.get(fragmentsStack.size() - 2);
    BaseFragment fragment=fragmentsStack.get(fragmentsStack.size() - 1);
    if (Build.VERSION.SDK_INT >= 21) {
      fragment.fragmentView.setOutlineProvider(null);
      fragment.fragmentView.setClipToOutline(false);
    }
    LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)fragment.fragmentView.getLayoutParams();
    layoutParams.topMargin=layoutParams.bottomMargin=layoutParams.rightMargin=layoutParams.leftMargin=0;
    fragment.fragmentView.setLayoutParams(layoutParams);
    presentFragmentInternalRemoveOld(false,prevFragment);
    AnimatorSet animatorSet=new AnimatorSet();
    animatorSet.playTogether(ObjectAnimator.ofFloat(fragment.fragmentView,"scaleX",1.0f,1.05f,1.0f),ObjectAnimator.ofFloat(fragment.fragmentView,"scaleY",1.0f,1.05f,1.0f));
    animatorSet.setDuration(200);
    animatorSet.setInterpolator(new CubicBezierInterpolator(0.42,0.0,0.58,1.0));
    animatorSet.start();
    performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
    fragment.setInPreviewMode(false);
  }
  if (currentTranslation != nextTranslation) {
    containerView.setTranslationY(nextTranslation);
    invalidate();
  }
}
