private void openCamera(boolean animated){
  if (cameraView == null) {
    return;
  }
  if (cameraPhotos.isEmpty()) {
    counterTextView.setVisibility(View.INVISIBLE);
    cameraPhotoRecyclerView.setVisibility(View.GONE);
  }
 else {
    counterTextView.setVisibility(View.VISIBLE);
    cameraPhotoRecyclerView.setVisibility(View.VISIBLE);
  }
  cameraPanel.setVisibility(View.VISIBLE);
  cameraPanel.setTag(null);
  animateCameraValues[0]=0;
  animateCameraValues[1]=AndroidUtilities.dp(80) - cameraViewOffsetX;
  animateCameraValues[2]=AndroidUtilities.dp(80) - cameraViewOffsetY;
  if (animated) {
    cameraAnimationInProgress=true;
    ArrayList<Animator> animators=new ArrayList<>();
    animators.add(ObjectAnimator.ofFloat(ChatAttachAlert.this,"cameraOpenProgress",0.0f,1.0f));
    animators.add(ObjectAnimator.ofFloat(cameraPanel,View.ALPHA,1.0f));
    animators.add(ObjectAnimator.ofFloat(counterTextView,View.ALPHA,1.0f));
    animators.add(ObjectAnimator.ofFloat(cameraPhotoRecyclerView,View.ALPHA,1.0f));
    for (int a=0; a < 2; a++) {
      if (flashModeButton[a].getVisibility() == View.VISIBLE) {
        animators.add(ObjectAnimator.ofFloat(flashModeButton[a],View.ALPHA,1.0f));
        break;
      }
    }
    AnimatorSet animatorSet=new AnimatorSet();
    animatorSet.playTogether(animators);
    animatorSet.setDuration(200);
    animatorSet.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animator){
        cameraAnimationInProgress=false;
        if (cameraOpened) {
          delegate.onCameraOpened();
        }
      }
    }
);
    animatorSet.start();
  }
 else {
    setCameraOpenProgress(1.0f);
    cameraPanel.setAlpha(1.0f);
    counterTextView.setAlpha(1.0f);
    cameraPhotoRecyclerView.setAlpha(1.0f);
    for (int a=0; a < 2; a++) {
      if (flashModeButton[a].getVisibility() == View.VISIBLE) {
        flashModeButton[a].setAlpha(1.0f);
        break;
      }
    }
    delegate.onCameraOpened();
  }
  if (Build.VERSION.SDK_INT >= 21) {
    cameraView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN);
  }
  cameraOpened=true;
  cameraView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
  if (Build.VERSION.SDK_INT >= 19) {
    attachPhotoRecyclerView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);
    for (    AttachButton button : attachButtons) {
      button.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);
    }
  }
}
