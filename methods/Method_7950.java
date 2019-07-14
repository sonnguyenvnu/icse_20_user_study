private boolean checkAudioMotionEvent(MotionEvent event){
  if (documentAttachType != DOCUMENT_ATTACH_TYPE_AUDIO && documentAttachType != DOCUMENT_ATTACH_TYPE_MUSIC) {
    return false;
  }
  int x=(int)event.getX();
  int y=(int)event.getY();
  boolean result;
  if (useSeekBarWaweform) {
    result=seekBarWaveform.onTouch(event.getAction(),event.getX() - seekBarX - AndroidUtilities.dp(13),event.getY() - seekBarY);
  }
 else {
    result=seekBar.onTouch(event.getAction(),event.getX() - seekBarX,event.getY() - seekBarY);
  }
  if (result) {
    if (!useSeekBarWaweform && event.getAction() == MotionEvent.ACTION_DOWN) {
      getParent().requestDisallowInterceptTouchEvent(true);
    }
 else     if (useSeekBarWaweform && !seekBarWaveform.isStartDraging() && event.getAction() == MotionEvent.ACTION_UP) {
      didPressButton(true,false);
    }
    disallowLongPress=true;
    invalidate();
  }
 else {
    int side=AndroidUtilities.dp(36);
    boolean area=false;
    boolean area2=false;
    if (miniButtonState >= 0) {
      int offset=AndroidUtilities.dp(27);
      area2=x >= buttonX + offset && x <= buttonX + offset + side && y >= buttonY + offset && y <= buttonY + offset + side;
    }
    if (!area2) {
      if (buttonState == 0 || buttonState == 1 || buttonState == 2) {
        area=x >= buttonX - AndroidUtilities.dp(12) && x <= buttonX - AndroidUtilities.dp(12) + backgroundWidth && y >= (drawInstantView ? buttonY : namesOffset + mediaOffsetY) && y <= (drawInstantView ? buttonY + side : namesOffset + mediaOffsetY + AndroidUtilities.dp(82));
      }
 else {
        area=x >= buttonX && x <= buttonX + side && y >= buttonY && y <= buttonY + side;
      }
    }
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      if (area || area2) {
        if (area) {
          buttonPressed=1;
        }
 else {
          miniButtonPressed=1;
        }
        invalidate();
        result=true;
      }
    }
 else     if (buttonPressed != 0) {
      if (event.getAction() == MotionEvent.ACTION_UP) {
        buttonPressed=0;
        playSoundEffect(SoundEffectConstants.CLICK);
        didPressButton(true,false);
        invalidate();
      }
 else       if (event.getAction() == MotionEvent.ACTION_CANCEL) {
        buttonPressed=0;
        invalidate();
      }
 else       if (event.getAction() == MotionEvent.ACTION_MOVE) {
        if (!area) {
          buttonPressed=0;
          invalidate();
        }
      }
    }
 else     if (miniButtonPressed != 0) {
      if (event.getAction() == MotionEvent.ACTION_UP) {
        miniButtonPressed=0;
        playSoundEffect(SoundEffectConstants.CLICK);
        didPressMiniButton(true);
        invalidate();
      }
 else       if (event.getAction() == MotionEvent.ACTION_CANCEL) {
        miniButtonPressed=0;
        invalidate();
      }
 else       if (event.getAction() == MotionEvent.ACTION_MOVE) {
        if (!area2) {
          miniButtonPressed=0;
          invalidate();
        }
      }
    }
  }
  return result;
}
