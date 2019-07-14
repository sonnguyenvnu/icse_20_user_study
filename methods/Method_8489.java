private boolean processTouchEvent(MotionEvent event){
  if (event == null) {
    return false;
  }
  if (!pressed && event.getActionMasked() == MotionEvent.ACTION_DOWN || event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
    if (!takingPhoto) {
      pressed=true;
      maybeStartDraging=true;
      lastY=event.getY();
    }
  }
 else   if (pressed) {
    if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
      float newY=event.getY();
      float dy=(newY - lastY);
      if (maybeStartDraging) {
        if (Math.abs(dy) > AndroidUtilities.getPixelsInCM(0.4f,false)) {
          maybeStartDraging=false;
          dragging=true;
        }
      }
 else       if (dragging) {
        if (cameraView != null) {
          cameraView.setTranslationY(cameraView.getTranslationY() + dy);
          lastY=newY;
          if (cameraPanel.getTag() == null) {
            cameraPanel.setTag(1);
            AnimatorSet animatorSet=new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(cameraPanel,"alpha",0.0f),ObjectAnimator.ofFloat(counterTextView,"alpha",0.0f),ObjectAnimator.ofFloat(flashModeButton[0],"alpha",0.0f),ObjectAnimator.ofFloat(flashModeButton[1],"alpha",0.0f),ObjectAnimator.ofFloat(cameraPhotoRecyclerView,"alpha",0.0f));
            animatorSet.setDuration(200);
            animatorSet.start();
          }
        }
      }
    }
 else     if (event.getActionMasked() == MotionEvent.ACTION_CANCEL || event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
      pressed=false;
      if (dragging) {
        dragging=false;
        if (cameraView != null) {
          if (Math.abs(cameraView.getTranslationY()) > cameraView.getMeasuredHeight() / 6.0f) {
            closeCamera(true);
          }
 else {
            AnimatorSet animatorSet=new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(cameraView,"translationY",0.0f),ObjectAnimator.ofFloat(cameraPanel,"alpha",1.0f),ObjectAnimator.ofFloat(counterTextView,"alpha",1.0f),ObjectAnimator.ofFloat(flashModeButton[0],"alpha",1.0f),ObjectAnimator.ofFloat(flashModeButton[1],"alpha",1.0f),ObjectAnimator.ofFloat(cameraPhotoRecyclerView,"alpha",1.0f));
            animatorSet.setDuration(250);
            animatorSet.setInterpolator(interpolator);
            animatorSet.start();
            cameraPanel.setTag(null);
          }
        }
      }
 else       if (cameraView != null) {
        cameraView.getLocationOnScreen(viewPosition);
        float viewX=event.getRawX() - viewPosition[0];
        float viewY=event.getRawY() - viewPosition[1];
        cameraView.focusToPoint((int)viewX,(int)viewY);
      }
    }
  }
  return true;
}
