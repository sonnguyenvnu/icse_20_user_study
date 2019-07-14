private boolean checkPhotoImageMotionEvent(MotionEvent event){
  if (!drawPhotoImage && documentAttachType != DOCUMENT_ATTACH_TYPE_DOCUMENT) {
    return false;
  }
  int x=(int)event.getX();
  int y=(int)event.getY();
  boolean result=false;
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    boolean area2=false;
    int side=AndroidUtilities.dp(48);
    if (miniButtonState >= 0) {
      int offset=AndroidUtilities.dp(27);
      area2=x >= buttonX + offset && x <= buttonX + offset + side && y >= buttonY + offset && y <= buttonY + offset + side;
    }
    if (area2) {
      miniButtonPressed=1;
      invalidate();
      result=true;
    }
 else     if (buttonState != -1 && radialProgress.getIcon() != MediaActionDrawable.ICON_NONE && x >= buttonX && x <= buttonX + side && y >= buttonY && y <= buttonY + side) {
      buttonPressed=1;
      invalidate();
      result=true;
    }
 else     if (drawVideoImageButton && buttonState != -1 && x >= videoButtonX && x <= videoButtonX + AndroidUtilities.dp(26 + 8) + Math.max(infoWidth,docTitleWidth) && y >= videoButtonY && y <= videoButtonY + AndroidUtilities.dp(30)) {
      videoButtonPressed=1;
      invalidate();
      result=true;
    }
 else {
      if (documentAttachType == DOCUMENT_ATTACH_TYPE_DOCUMENT) {
        if (x >= photoImage.getImageX() && x <= photoImage.getImageX() + backgroundWidth - AndroidUtilities.dp(50) && y >= photoImage.getImageY() && y <= photoImage.getImageY() + photoImage.getImageHeight()) {
          imagePressed=true;
          result=true;
        }
      }
 else       if (!currentMessageObject.isAnyKindOfSticker() || currentMessageObject.getInputStickerSet() != null) {
        if (x >= photoImage.getImageX() && x <= photoImage.getImageX() + photoImage.getImageWidth() && y >= photoImage.getImageY() && y <= photoImage.getImageY() + photoImage.getImageHeight()) {
          imagePressed=true;
          result=true;
        }
        if (currentMessageObject.type == 12) {
          TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(currentMessageObject.messageOwner.media.user_id);
          if (user == null) {
            imagePressed=false;
            result=false;
          }
        }
      }
    }
    if (imagePressed) {
      if (currentMessageObject.isSendError()) {
        imagePressed=false;
        result=false;
      }
 else       if (currentMessageObject.type == 8 && buttonState == -1 && SharedConfig.autoplayGifs && photoImage.getAnimation() == null) {
        imagePressed=false;
        result=false;
      }
    }
  }
 else {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      if (videoButtonPressed == 1) {
        videoButtonPressed=0;
        playSoundEffect(SoundEffectConstants.CLICK);
        didPressButton(true,true);
        invalidate();
      }
 else       if (buttonPressed == 1) {
        buttonPressed=0;
        playSoundEffect(SoundEffectConstants.CLICK);
        if (drawVideoImageButton) {
          didClickedImage();
        }
 else {
          didPressButton(true,false);
        }
        invalidate();
      }
 else       if (miniButtonPressed == 1) {
        miniButtonPressed=0;
        playSoundEffect(SoundEffectConstants.CLICK);
        didPressMiniButton(true);
        invalidate();
      }
 else       if (imagePressed) {
        imagePressed=false;
        if (buttonState == -1 || buttonState == 2 || buttonState == 3 || drawVideoImageButton) {
          playSoundEffect(SoundEffectConstants.CLICK);
          didClickedImage();
        }
 else         if (buttonState == 0) {
          playSoundEffect(SoundEffectConstants.CLICK);
          didPressButton(true,false);
        }
        invalidate();
      }
    }
  }
  return result;
}
