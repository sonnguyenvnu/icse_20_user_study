private boolean checkAudioMotionEvent(MotionEvent event){
  int x=(int)event.getX();
  int y=(int)event.getY();
  boolean result=false;
  int side=AndroidUtilities.dp(36);
  boolean area=false;
  if (miniButtonState >= 0) {
    int offset=AndroidUtilities.dp(27);
    area=x >= buttonX + offset && x <= buttonX + offset + side && y >= buttonY + offset && y <= buttonY + offset + side;
  }
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    if (area) {
      miniButtonPressed=true;
      radialProgress.setPressed(miniButtonPressed,true);
      invalidate();
      result=true;
    }
  }
 else   if (miniButtonPressed) {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      miniButtonPressed=false;
      playSoundEffect(SoundEffectConstants.CLICK);
      didPressedMiniButton(true);
      invalidate();
    }
 else     if (event.getAction() == MotionEvent.ACTION_CANCEL) {
      miniButtonPressed=false;
      invalidate();
    }
 else     if (event.getAction() == MotionEvent.ACTION_MOVE) {
      if (!area) {
        miniButtonPressed=false;
        invalidate();
      }
    }
    radialProgress.setPressed(miniButtonPressed,true);
  }
  return result;
}
