private void didPressButton(boolean animated,boolean video){
  if (buttonState == 0 && (!drawVideoImageButton || video)) {
    if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
      if (miniButtonState == 0) {
        FileLoader.getInstance(currentAccount).loadFile(documentAttach,currentMessageObject,1,0);
      }
      if (delegate.needPlayMessage(currentMessageObject)) {
        if (hasMiniProgress == 2 && miniButtonState != 1) {
          miniButtonState=1;
          radialProgress.setProgress(0,false);
          radialProgress.setMiniIcon(getMiniIconForCurrentState(),false,true);
        }
        updatePlayingMessageProgress();
        buttonState=1;
        radialProgress.setIcon(getIconForCurrentState(),false,true);
        invalidate();
      }
    }
 else {
      cancelLoading=false;
      if (video) {
        videoRadialProgress.setProgress(0,false);
      }
 else {
        radialProgress.setProgress(0,false);
      }
      TLRPC.PhotoSize thumb;
      String thumbFilter;
      if (currentPhotoObject != null && (photoImage.hasNotThumb() || currentPhotoObjectThumb == null)) {
        thumb=currentPhotoObject;
        thumbFilter=thumb instanceof TLRPC.TL_photoStrippedSize || "s".equals(thumb.type) ? currentPhotoFilterThumb : currentPhotoFilter;
      }
 else {
        thumb=currentPhotoObjectThumb;
        thumbFilter=currentPhotoFilterThumb;
      }
      if (currentMessageObject.type == 1) {
        photoImage.setForceLoading(true);
        photoImage.setImage(ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,currentPhotoObject.size,null,currentMessageObject,currentMessageObject.shouldEncryptPhotoOrVideo() ? 2 : 0);
      }
 else       if (currentMessageObject.type == 8) {
        FileLoader.getInstance(currentAccount).loadFile(documentAttach,currentMessageObject,1,0);
      }
 else       if (currentMessageObject.isRoundVideo()) {
        if (currentMessageObject.isSecretMedia()) {
          FileLoader.getInstance(currentAccount).loadFile(currentMessageObject.getDocument(),currentMessageObject,1,1);
        }
 else {
          currentMessageObject.gifState=2;
          TLRPC.Document document=currentMessageObject.getDocument();
          photoImage.setForceLoading(true);
          photoImage.setImage(ImageLocation.getForDocument(document),null,ImageLocation.getForObject(thumb,document),thumbFilter,document.size,null,currentMessageObject,0);
        }
      }
 else       if (currentMessageObject.type == 9) {
        FileLoader.getInstance(currentAccount).loadFile(documentAttach,currentMessageObject,0,0);
      }
 else       if (documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO) {
        FileLoader.getInstance(currentAccount).loadFile(documentAttach,currentMessageObject,1,currentMessageObject.shouldEncryptPhotoOrVideo() ? 2 : 0);
      }
 else       if (currentMessageObject.type == 0 && documentAttachType != DOCUMENT_ATTACH_TYPE_NONE) {
        if (documentAttachType == DOCUMENT_ATTACH_TYPE_GIF) {
          photoImage.setForceLoading(true);
          photoImage.setImage(ImageLocation.getForDocument(documentAttach),null,ImageLocation.getForDocument(currentPhotoObject,documentAttach),currentPhotoFilterThumb,documentAttach.size,null,currentMessageObject,0);
          currentMessageObject.gifState=2;
        }
 else         if (documentAttachType == DOCUMENT_ATTACH_TYPE_DOCUMENT) {
          FileLoader.getInstance(currentAccount).loadFile(documentAttach,currentMessageObject,0,0);
        }
 else         if (documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER) {
          photoImage.setImage(ImageLocation.getForDocument(documentAttach),currentPhotoFilter,ImageLocation.getForDocument(currentPhotoObject,documentAttach),"b1",0,"jpg",currentMessageObject,1);
        }
      }
 else {
        photoImage.setForceLoading(true);
        photoImage.setImage(ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,0,null,currentMessageObject,0);
      }
      buttonState=1;
      if (video) {
        videoRadialProgress.setIcon(MediaActionDrawable.ICON_CANCEL_FILL,false,animated);
      }
 else {
        radialProgress.setIcon(getIconForCurrentState(),false,animated);
      }
      invalidate();
    }
  }
 else   if (buttonState == 1 && (!drawVideoImageButton || video)) {
    photoImage.setForceLoading(false);
    if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
      boolean result=MediaController.getInstance().pauseMessage(currentMessageObject);
      if (result) {
        buttonState=0;
        radialProgress.setIcon(getIconForCurrentState(),false,animated);
        invalidate();
      }
    }
 else {
      if (currentMessageObject.isOut() && (currentMessageObject.isSending() || currentMessageObject.isEditing())) {
        if (radialProgress.getIcon() != MediaActionDrawable.ICON_CHECK) {
          delegate.didPressCancelSendButton(this);
        }
      }
 else {
        cancelLoading=true;
        if (documentAttachType == DOCUMENT_ATTACH_TYPE_GIF || documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_DOCUMENT || documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER) {
          FileLoader.getInstance(currentAccount).cancelLoadFile(documentAttach);
        }
 else         if (currentMessageObject.type == 0 || currentMessageObject.type == 1 || currentMessageObject.type == 8 || currentMessageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
          ImageLoader.getInstance().cancelForceLoadingForImageReceiver(photoImage);
          photoImage.cancelLoadImage();
        }
 else         if (currentMessageObject.type == 9) {
          FileLoader.getInstance(currentAccount).cancelLoadFile(currentMessageObject.messageOwner.media.document);
        }
        buttonState=0;
        if (video) {
          videoRadialProgress.setIcon(MediaActionDrawable.ICON_DOWNLOAD,false,animated);
        }
 else {
          radialProgress.setIcon(getIconForCurrentState(),false,animated);
        }
        invalidate();
      }
    }
  }
 else   if (buttonState == 2) {
    if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
      radialProgress.setProgress(0,false);
      FileLoader.getInstance(currentAccount).loadFile(documentAttach,currentMessageObject,1,0);
      buttonState=4;
      radialProgress.setIcon(getIconForCurrentState(),true,animated);
      invalidate();
    }
 else {
      if (currentMessageObject.isRoundVideo()) {
        MessageObject playingMessage=MediaController.getInstance().getPlayingMessageObject();
        if (playingMessage == null || !playingMessage.isRoundVideo()) {
          photoImage.setAllowStartAnimation(true);
          photoImage.startAnimation();
        }
      }
 else {
        photoImage.setAllowStartAnimation(true);
        photoImage.startAnimation();
      }
      currentMessageObject.gifState=0;
      buttonState=-1;
      radialProgress.setIcon(getIconForCurrentState(),false,animated);
    }
  }
 else   if (buttonState == 3 || buttonState == 0 && drawVideoImageButton) {
    if (hasMiniProgress == 2 && miniButtonState != 1) {
      miniButtonState=1;
      radialProgress.setProgress(0,false);
      radialProgress.setMiniIcon(getMiniIconForCurrentState(),false,animated);
    }
    delegate.didPressImage(this,0,0);
  }
 else   if (buttonState == 4) {
    if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
      if (currentMessageObject.isOut() && (currentMessageObject.isSending() || currentMessageObject.isEditing()) || currentMessageObject.isSendError()) {
        if (delegate != null) {
          delegate.didPressCancelSendButton(this);
        }
      }
 else {
        FileLoader.getInstance(currentAccount).cancelLoadFile(documentAttach);
        buttonState=2;
        radialProgress.setIcon(getIconForCurrentState(),false,animated);
        invalidate();
      }
    }
  }
}
