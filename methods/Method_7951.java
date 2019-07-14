private boolean checkBotButtonMotionEvent(MotionEvent event){
  if (botButtons.isEmpty() || currentMessageObject.eventId != 0) {
    return false;
  }
  int x=(int)event.getX();
  int y=(int)event.getY();
  boolean result=false;
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    int addX;
    if (currentMessageObject.isOutOwner()) {
      addX=getMeasuredWidth() - widthForButtons - AndroidUtilities.dp(10);
    }
 else {
      addX=backgroundDrawableLeft + AndroidUtilities.dp(mediaBackground ? 1 : 7);
    }
    for (int a=0; a < botButtons.size(); a++) {
      BotButton button=botButtons.get(a);
      int y2=button.y + layoutHeight - AndroidUtilities.dp(2);
      if (x >= button.x + addX && x <= button.x + addX + button.width && y >= y2 && y <= y2 + button.height) {
        pressedBotButton=a;
        invalidate();
        result=true;
        break;
      }
    }
  }
 else {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      if (pressedBotButton != -1) {
        playSoundEffect(SoundEffectConstants.CLICK);
        delegate.didPressBotButton(this,botButtons.get(pressedBotButton).button);
        pressedBotButton=-1;
        invalidate();
      }
    }
  }
  return result;
}
