private boolean checkOtherButtonMotionEvent(MotionEvent event){
  boolean allow=currentMessageObject.type == 16;
  if (!allow) {
    allow=!(documentAttachType != DOCUMENT_ATTACH_TYPE_DOCUMENT && currentMessageObject.type != 12 && documentAttachType != DOCUMENT_ATTACH_TYPE_MUSIC && documentAttachType != DOCUMENT_ATTACH_TYPE_VIDEO && documentAttachType != DOCUMENT_ATTACH_TYPE_GIF && currentMessageObject.type != 8 || hasGamePreview || hasInvoicePreview);
  }
  if (!allow) {
    return false;
  }
  int x=(int)event.getX();
  int y=(int)event.getY();
  boolean result=false;
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    if (currentMessageObject.type == 16) {
      if (x >= otherX && x <= otherX + AndroidUtilities.dp(30 + 205) && y >= otherY - AndroidUtilities.dp(14) && y <= otherY + AndroidUtilities.dp(50)) {
        otherPressed=true;
        result=true;
        invalidate();
      }
    }
 else {
      if (x >= otherX - AndroidUtilities.dp(20) && x <= otherX + AndroidUtilities.dp(20) && y >= otherY - AndroidUtilities.dp(4) && y <= otherY + AndroidUtilities.dp(30)) {
        otherPressed=true;
        result=true;
        invalidate();
      }
    }
  }
 else {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      if (otherPressed) {
        otherPressed=false;
        playSoundEffect(SoundEffectConstants.CLICK);
        delegate.didPressOther(this,otherX,otherY);
        invalidate();
        result=true;
      }
    }
  }
  return result;
}
