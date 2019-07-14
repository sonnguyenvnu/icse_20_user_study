public void closeCamera(boolean animated){
  if (takingPhoto || cameraView == null) {
    return;
  }
  animateCameraValues[1]=AndroidUtilities.dp(80) - cameraViewOffsetX;
  animateCameraValues[2]=AndroidUtilities.dp(80) - cameraViewOffsetY;
  if (animated) {
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)cameraView.getLayoutParams();
    animateCameraValues[0]=layoutParams.topMargin=(int)cameraView.getTranslationY();
    cameraView.setLayoutParams(layoutParams);
    cameraView.setTranslationY(0);
    cameraAnimationInProgress=true;
    ArrayList<Animator> animators=new ArrayList<>();
    animators.add(ObjectAnimator.ofFloat(ChatAttachAlert.this,"cameraOpenProgress",0.0f));
    animators.add(ObjectAnimator.ofFloat(cameraPanel,"alpha",0.0f));
    animators.add(ObjectAnimator.ofFloat(counterTextView,"alpha",0.0f));
    animators.add(ObjectAnimator.ofFloat(cameraPhotoRecyclerView,"alpha",0.0f));
    for (int a=0; a < 2; a++) {
      if (flashModeButton[a].getVisibility() == View.VISIBLE) {
        animators.add(ObjectAnimator.ofFloat(flashModeButton[a],"alpha",0.0f));
        break;
      }
    }
    AnimatorSet animatorSet=new AnimatorSet();
    animatorSet.playTogether(animators);
    animatorSet.setDuration(200);
    animatorSet.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animator){
        cameraAnimationInProgress=false;
        cameraOpened=false;
        if (cameraPanel != null) {
          cameraPanel.setVisibility(View.GONE);
        }
        if (cameraPhotoRecyclerView != null) {
          cameraPhotoRecyclerView.setVisibility(View.GONE);
        }
        if (Build.VERSION.SDK_INT >= 21 && cameraView != null) {
          cameraView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
      }
    }
);
    animatorSet.start();
  }
 else {
    animateCameraValues[0]=0;
    setCameraOpenProgress(0);
    cameraPanel.setAlpha(0);
    cameraPhotoRecyclerView.setAlpha(0);
    counterTextView.setAlpha(0);
    cameraPanel.setVisibility(View.GONE);
    cameraPhotoRecyclerView.setVisibility(View.GONE);
    for (int a=0; a < 2; a++) {
      if (flashModeButton[a].getVisibility() == View.VISIBLE) {
        flashModeButton[a].setAlpha(0.0f);
        break;
      }
    }
    cameraOpened=false;
    if (Build.VERSION.SDK_INT >= 21) {
      cameraView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
  }
  cameraView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_AUTO);
  if (Build.VERSION.SDK_INT >= 19) {
    attachPhotoRecyclerView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_AUTO);
    for (    AttachButton button : attachButtons) {
      button.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_AUTO);
    }
  }
}
