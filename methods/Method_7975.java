public void updateButtonState(boolean ifSame,boolean animated,boolean fromSet){
  if (animated && (PhotoViewer.isShowingImage(currentMessageObject) || !attachedToWindow)) {
    animated=false;
  }
  drawRadialCheckBackground=false;
  String fileName=null;
  boolean fileExists=false;
  if (currentMessageObject.type == 1) {
    if (currentPhotoObject == null) {
      radialProgress.setIcon(MediaActionDrawable.ICON_NONE,ifSame,animated);
      return;
    }
    fileName=FileLoader.getAttachFileName(currentPhotoObject);
    fileExists=currentMessageObject.mediaExists;
  }
 else   if (currentMessageObject.type == 8 || documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND || documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER || currentMessageObject.type == 9 || documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
    if (currentMessageObject.useCustomPhoto) {
      buttonState=1;
      radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
      return;
    }
    if (currentMessageObject.attachPathExists && !TextUtils.isEmpty(currentMessageObject.messageOwner.attachPath)) {
      fileName=currentMessageObject.messageOwner.attachPath;
      fileExists=true;
    }
 else     if (!currentMessageObject.isSendError() || documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
      fileName=currentMessageObject.getFileName();
      fileExists=currentMessageObject.mediaExists;
    }
  }
 else   if (documentAttachType != DOCUMENT_ATTACH_TYPE_NONE) {
    fileName=FileLoader.getAttachFileName(documentAttach);
    fileExists=currentMessageObject.mediaExists;
  }
 else   if (currentPhotoObject != null) {
    fileName=FileLoader.getAttachFileName(currentPhotoObject);
    fileExists=currentMessageObject.mediaExists;
  }
  boolean autoDownload=DownloadController.getInstance(currentAccount).canDownloadMedia(currentMessageObject);
  canStreamVideo=currentMessageObject.isSent() && (documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF && autoDownload) && currentMessageObject.canStreamVideo() && !currentMessageObject.needDrawBluredPreview();
  if (SharedConfig.streamMedia && (int)currentMessageObject.getDialogId() != 0 && !currentMessageObject.isSecretMedia() && (documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC || canStreamVideo && currentPosition != null && ((currentPosition.flags & MessageObject.POSITION_FLAG_LEFT) == 0 || (currentPosition.flags & MessageObject.POSITION_FLAG_RIGHT) == 0))) {
    hasMiniProgress=fileExists ? 1 : 2;
    fileExists=true;
  }
  if (currentMessageObject.isSendError() || TextUtils.isEmpty(fileName) && !currentMessageObject.isSending() && !currentMessageObject.isEditing()) {
    radialProgress.setIcon(MediaActionDrawable.ICON_NONE,ifSame,false);
    radialProgress.setMiniIcon(MediaActionDrawable.ICON_NONE,ifSame,false);
    videoRadialProgress.setIcon(MediaActionDrawable.ICON_NONE,ifSame,false);
    videoRadialProgress.setMiniIcon(MediaActionDrawable.ICON_NONE,ifSame,false);
    return;
  }
  boolean fromBot=currentMessageObject.messageOwner.params != null && currentMessageObject.messageOwner.params.containsKey("query_id");
  if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
    if (currentMessageObject.isOut() && (currentMessageObject.isSending() || currentMessageObject.isEditing()) || currentMessageObject.isSendError() && fromBot) {
      if (!TextUtils.isEmpty(currentMessageObject.messageOwner.attachPath)) {
        DownloadController.getInstance(currentAccount).addLoadingFileObserver(currentMessageObject.messageOwner.attachPath,currentMessageObject,this);
        wasSending=true;
        buttonState=4;
        radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
        if (!fromBot) {
          Float progress=ImageLoader.getInstance().getFileProgress(currentMessageObject.messageOwner.attachPath);
          if (progress == null && SendMessagesHelper.getInstance(currentAccount).isSendingMessage(currentMessageObject.getId())) {
            progress=1.0f;
          }
          radialProgress.setProgress(progress != null ? progress : 0,false);
        }
 else {
          radialProgress.setProgress(0,false);
        }
      }
 else {
        buttonState=-1;
        getIconForCurrentState();
        radialProgress.setIcon(MediaActionDrawable.ICON_CANCEL_NOPROFRESS,ifSame,false);
        radialProgress.setProgress(0,false);
      }
    }
 else {
      if (hasMiniProgress != 0) {
        radialProgress.setMiniProgressBackgroundColor(Theme.getColor(currentMessageObject.isOutOwner() ? Theme.key_chat_outLoader : Theme.key_chat_inLoader));
        boolean playing=MediaController.getInstance().isPlayingMessage(currentMessageObject);
        if (!playing || playing && MediaController.getInstance().isMessagePaused()) {
          buttonState=0;
        }
 else {
          buttonState=1;
        }
        radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
        if (hasMiniProgress == 1) {
          DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
          miniButtonState=-1;
        }
 else {
          DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,currentMessageObject,this);
          if (!FileLoader.getInstance(currentAccount).isLoadingFile(fileName)) {
            miniButtonState=0;
          }
 else {
            miniButtonState=1;
            Float progress=ImageLoader.getInstance().getFileProgress(fileName);
            if (progress != null) {
              radialProgress.setProgress(progress,animated);
            }
 else {
              radialProgress.setProgress(0,animated);
            }
          }
        }
        radialProgress.setMiniIcon(getMiniIconForCurrentState(),ifSame,animated);
      }
 else       if (fileExists) {
        DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
        boolean playing=MediaController.getInstance().isPlayingMessage(currentMessageObject);
        if (!playing || playing && MediaController.getInstance().isMessagePaused()) {
          buttonState=0;
        }
 else {
          buttonState=1;
        }
        radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
      }
 else {
        DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,currentMessageObject,this);
        if (!FileLoader.getInstance(currentAccount).isLoadingFile(fileName)) {
          buttonState=2;
          radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
        }
 else {
          buttonState=4;
          Float progress=ImageLoader.getInstance().getFileProgress(fileName);
          if (progress != null) {
            radialProgress.setProgress(progress,animated);
          }
 else {
            radialProgress.setProgress(0,animated);
          }
          radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
        }
      }
    }
    updatePlayingMessageProgress();
  }
 else   if (currentMessageObject.type == 0 && documentAttachType != DOCUMENT_ATTACH_TYPE_DOCUMENT && documentAttachType != DOCUMENT_ATTACH_TYPE_GIF && documentAttachType != DOCUMENT_ATTACH_TYPE_ROUND && documentAttachType != DOCUMENT_ATTACH_TYPE_VIDEO && documentAttachType != DOCUMENT_ATTACH_TYPE_WALLPAPER) {
    if (currentPhotoObject == null || !drawImageButton) {
      return;
    }
    if (!fileExists) {
      DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,currentMessageObject,this);
      float setProgress=0;
      if (!FileLoader.getInstance(currentAccount).isLoadingFile(fileName)) {
        if (!cancelLoading && (documentAttachType == 0 && autoDownload || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF && MessageObject.isGifDocument(documentAttach) && autoDownload)) {
          buttonState=1;
        }
 else {
          buttonState=0;
        }
      }
 else {
        buttonState=1;
        Float progress=ImageLoader.getInstance().getFileProgress(fileName);
        setProgress=progress != null ? progress : 0;
      }
      radialProgress.setProgress(setProgress,false);
      radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
      invalidate();
    }
 else {
      DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
      if (documentAttachType == DOCUMENT_ATTACH_TYPE_GIF && !photoImage.isAllowStartAnimation()) {
        buttonState=2;
      }
 else {
        buttonState=-1;
      }
      radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
      invalidate();
    }
  }
 else {
    if (currentMessageObject.isOut() && (currentMessageObject.isSending() || currentMessageObject.isEditing())) {
      if (!TextUtils.isEmpty(currentMessageObject.messageOwner.attachPath)) {
        DownloadController.getInstance(currentAccount).addLoadingFileObserver(currentMessageObject.messageOwner.attachPath,currentMessageObject,this);
        wasSending=true;
        boolean needProgress=currentMessageObject.messageOwner.attachPath == null || !currentMessageObject.messageOwner.attachPath.startsWith("http");
        HashMap<String,String> params=currentMessageObject.messageOwner.params;
        if (currentMessageObject.messageOwner.message != null && params != null && (params.containsKey("url") || params.containsKey("bot"))) {
          needProgress=false;
          buttonState=-1;
        }
 else {
          buttonState=1;
        }
        boolean sending=SendMessagesHelper.getInstance(currentAccount).isSendingMessage(currentMessageObject.getId());
        if (currentPosition != null && sending && buttonState == 1) {
          drawRadialCheckBackground=true;
          getIconForCurrentState();
          radialProgress.setIcon(MediaActionDrawable.ICON_CHECK,ifSame,animated);
        }
 else {
          radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
        }
        if (needProgress) {
          Float progress=ImageLoader.getInstance().getFileProgress(currentMessageObject.messageOwner.attachPath);
          if (progress == null && sending) {
            progress=1.0f;
          }
          radialProgress.setProgress(progress != null ? progress : 0,false);
        }
 else {
          radialProgress.setProgress(0,false);
        }
        invalidate();
      }
 else {
        buttonState=-1;
        getIconForCurrentState();
        radialProgress.setIcon(currentMessageObject.isSticker() || currentMessageObject.isAnimatedSticker() || currentMessageObject.isLocation() ? MediaActionDrawable.ICON_NONE : MediaActionDrawable.ICON_CANCEL_NOPROFRESS,ifSame,false);
        radialProgress.setProgress(0,false);
      }
      videoRadialProgress.setIcon(MediaActionDrawable.ICON_NONE,ifSame,false);
    }
 else {
      if (wasSending && !TextUtils.isEmpty(currentMessageObject.messageOwner.attachPath)) {
        DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
      }
      boolean isLoadingVideo=false;
      if ((documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF || documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND) && autoPlayingMedia) {
        isLoadingVideo=FileLoader.getInstance(currentAccount).isLoadingVideo(documentAttach,MediaController.getInstance().isPlayingMessage(currentMessageObject));
        AnimatedFileDrawable animation=photoImage.getAnimation();
        if (animation != null) {
          if (currentMessageObject.hadAnimationNotReadyLoading) {
            if (animation.hasBitmap()) {
              currentMessageObject.hadAnimationNotReadyLoading=false;
            }
          }
 else {
            currentMessageObject.hadAnimationNotReadyLoading=isLoadingVideo && !animation.hasBitmap();
          }
        }
 else         if (documentAttachType == DOCUMENT_ATTACH_TYPE_GIF && !fileExists) {
          currentMessageObject.hadAnimationNotReadyLoading=true;
        }
      }
      if (hasMiniProgress != 0) {
        radialProgress.setMiniProgressBackgroundColor(Theme.getColor(Theme.key_chat_inLoaderPhoto));
        buttonState=3;
        radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
        if (hasMiniProgress == 1) {
          DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
          miniButtonState=-1;
        }
 else {
          DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,currentMessageObject,this);
          if (!FileLoader.getInstance(currentAccount).isLoadingFile(fileName)) {
            miniButtonState=0;
          }
 else {
            miniButtonState=1;
            Float progress=ImageLoader.getInstance().getFileProgress(fileName);
            if (progress != null) {
              radialProgress.setProgress(progress,animated);
            }
 else {
              radialProgress.setProgress(0,animated);
            }
          }
        }
        radialProgress.setMiniIcon(getMiniIconForCurrentState(),ifSame,animated);
      }
 else       if (fileExists || (documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF || documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND) && autoPlayingMedia && !currentMessageObject.hadAnimationNotReadyLoading && !isLoadingVideo) {
        DownloadController.getInstance(currentAccount).removeLoadingFileObserver(this);
        if (drawVideoImageButton && animated) {
          if (animatingDrawVideoImageButton != 1 && animatingDrawVideoImageButtonProgress > 0) {
            if (animatingDrawVideoImageButton == 0) {
              animatingDrawVideoImageButtonProgress=1.0f;
            }
            animatingDrawVideoImageButton=1;
          }
        }
 else         if (animatingDrawVideoImageButton == 0) {
          animatingDrawVideoImageButtonProgress=0.0f;
        }
        drawVideoImageButton=false;
        drawVideoSize=false;
        if (currentMessageObject.needDrawBluredPreview()) {
          buttonState=-1;
        }
 else {
          if (currentMessageObject.type == 8 && currentMessageObject.gifState == 1) {
            buttonState=2;
          }
 else           if (documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO) {
            buttonState=3;
          }
 else {
            buttonState=-1;
          }
        }
        videoRadialProgress.setIcon(MediaActionDrawable.ICON_NONE,ifSame,animatingDrawVideoImageButton != 0);
        radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
        if (!fromSet && photoNotSet) {
          setMessageObject(currentMessageObject,currentMessagesGroup,pinnedBottom,pinnedTop);
        }
        invalidate();
      }
 else {
        drawVideoSize=documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF;
        if ((documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF || documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND) && canStreamVideo && !drawVideoImageButton && animated) {
          if (animatingDrawVideoImageButton != 2 && animatingDrawVideoImageButtonProgress < 1.0f) {
            if (animatingDrawVideoImageButton == 0) {
              animatingDrawVideoImageButtonProgress=0.0f;
            }
            animatingDrawVideoImageButton=2;
          }
        }
 else         if (animatingDrawVideoImageButton == 0) {
          animatingDrawVideoImageButtonProgress=1.0f;
        }
        DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,currentMessageObject,this);
        boolean progressVisible=false;
        if (!FileLoader.getInstance(currentAccount).isLoadingFile(fileName)) {
          if (!cancelLoading && autoDownload) {
            buttonState=1;
          }
 else {
            buttonState=0;
          }
          if ((documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF && autoDownload) && canStreamVideo) {
            drawVideoImageButton=true;
            getIconForCurrentState();
            radialProgress.setIcon(autoPlayingMedia ? MediaActionDrawable.ICON_NONE : MediaActionDrawable.ICON_PLAY,ifSame,animated);
            videoRadialProgress.setIcon(MediaActionDrawable.ICON_DOWNLOAD,ifSame,animated);
          }
 else {
            drawVideoImageButton=false;
            radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
            videoRadialProgress.setIcon(MediaActionDrawable.ICON_NONE,ifSame,false);
            if (!drawVideoSize && animatingDrawVideoImageButton == 0) {
              animatingDrawVideoImageButtonProgress=0.0f;
            }
          }
        }
 else {
          buttonState=1;
          Float progress=ImageLoader.getInstance().getFileProgress(fileName);
          if ((documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF && autoDownload) && canStreamVideo) {
            drawVideoImageButton=true;
            getIconForCurrentState();
            radialProgress.setIcon(autoPlayingMedia || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF ? MediaActionDrawable.ICON_NONE : MediaActionDrawable.ICON_PLAY,ifSame,animated);
            videoRadialProgress.setProgress(progress != null ? progress : 0,animated);
            videoRadialProgress.setIcon(MediaActionDrawable.ICON_CANCEL_FILL,ifSame,animated);
          }
 else {
            drawVideoImageButton=false;
            radialProgress.setProgress(progress != null ? progress : 0,animated);
            radialProgress.setIcon(getIconForCurrentState(),ifSame,animated);
            videoRadialProgress.setIcon(MediaActionDrawable.ICON_NONE,ifSame,false);
            if (!drawVideoSize && animatingDrawVideoImageButton == 0) {
              animatingDrawVideoImageButtonProgress=0.0f;
            }
          }
        }
        invalidate();
      }
    }
  }
  if (hasMiniProgress == 0) {
    radialProgress.setMiniIcon(MediaActionDrawable.ICON_NONE,false,animated);
  }
}
