private void checkProgress(int a,boolean animated){
  int index=currentIndex;
  if (a == 1) {
    index+=1;
  }
 else   if (a == 2) {
    index-=1;
  }
  if (currentFileNames[a] != null) {
    File f=null;
    boolean isVideo=false;
    boolean canStream=false;
    if (currentMessageObject != null) {
      if (index < 0 || index >= imagesArr.size()) {
        photoProgressViews[a].setBackgroundState(-1,animated);
        return;
      }
      MessageObject messageObject=imagesArr.get(index);
      if (sharedMediaType == DataQuery.MEDIA_FILE && !messageObject.canPreviewDocument()) {
        photoProgressViews[a].setBackgroundState(-1,animated);
        return;
      }
      if (!TextUtils.isEmpty(messageObject.messageOwner.attachPath)) {
        f=new File(messageObject.messageOwner.attachPath);
        if (!f.exists()) {
          f=null;
        }
      }
      if (f == null) {
        if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && messageObject.messageOwner.media.webpage != null && messageObject.messageOwner.media.webpage.document == null) {
          TLObject fileLocation=getFileLocation(index,null);
          f=FileLoader.getPathToAttach(fileLocation,true);
        }
 else {
          f=FileLoader.getPathToMessage(messageObject.messageOwner);
        }
      }
      canStream=SharedConfig.streamMedia && messageObject.isVideo() && messageObject.canStreamVideo() && (int)messageObject.getDialogId() != 0;
      isVideo=messageObject.isVideo();
    }
 else     if (currentBotInlineResult != null) {
      if (index < 0 || index >= imagesArrLocals.size()) {
        photoProgressViews[a].setBackgroundState(-1,animated);
        return;
      }
      TLRPC.BotInlineResult botInlineResult=(TLRPC.BotInlineResult)imagesArrLocals.get(index);
      if (botInlineResult.type.equals("video") || MessageObject.isVideoDocument(botInlineResult.document)) {
        if (botInlineResult.document != null) {
          f=FileLoader.getPathToAttach(botInlineResult.document);
        }
 else         if (botInlineResult.content instanceof TLRPC.TL_webDocument) {
          f=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),Utilities.MD5(botInlineResult.content.url) + "." + ImageLoader.getHttpUrlExtension(botInlineResult.content.url,"mp4"));
        }
        isVideo=true;
      }
 else       if (botInlineResult.document != null) {
        f=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_DOCUMENT),currentFileNames[a]);
      }
 else       if (botInlineResult.photo != null) {
        f=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_IMAGE),currentFileNames[a]);
      }
      if (f == null || !f.exists()) {
        f=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),currentFileNames[a]);
      }
    }
 else     if (currentFileLocation != null) {
      if (index < 0 || index >= imagesArrLocations.size()) {
        photoProgressViews[a].setBackgroundState(-1,animated);
        return;
      }
      ImageLocation location=imagesArrLocations.get(index);
      f=FileLoader.getPathToAttach(location.location,avatarsDialogId != 0 || isEvent);
    }
 else     if (currentSecureDocument != null) {
      if (index < 0 || index >= secureDocuments.size()) {
        photoProgressViews[a].setBackgroundState(-1,animated);
        return;
      }
      SecureDocument location=secureDocuments.get(index);
      f=FileLoader.getPathToAttach(location,true);
    }
 else     if (currentPathObject != null) {
      f=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_DOCUMENT),currentFileNames[a]);
      if (!f.exists()) {
        f=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),currentFileNames[a]);
      }
    }
    boolean exists=f != null && f.exists();
    if (f != null && (exists || canStream)) {
      if (isVideo) {
        photoProgressViews[a].setBackgroundState(3,animated);
      }
 else {
        photoProgressViews[a].setBackgroundState(-1,animated);
      }
      if (a == 0) {
        if (!exists) {
          if (!FileLoader.getInstance(currentAccount).isLoadingFile(currentFileNames[a])) {
            menuItem.hideSubItem(gallery_menu_cancel_loading);
          }
 else {
            menuItem.showSubItem(gallery_menu_cancel_loading);
          }
        }
 else {
          menuItem.hideSubItem(gallery_menu_cancel_loading);
        }
      }
    }
 else {
      if (isVideo) {
        if (!FileLoader.getInstance(currentAccount).isLoadingFile(currentFileNames[a])) {
          photoProgressViews[a].setBackgroundState(2,false);
        }
 else {
          photoProgressViews[a].setBackgroundState(1,false);
        }
      }
 else {
        photoProgressViews[a].setBackgroundState(0,animated);
      }
      Float progress=ImageLoader.getInstance().getFileProgress(currentFileNames[a]);
      if (progress == null) {
        progress=0.0f;
      }
      photoProgressViews[a].setProgress(progress,false);
    }
    if (a == 0) {
      canZoom=!imagesArrLocals.isEmpty() || (currentFileNames[0] != null && photoProgressViews[0].backgroundState != 0);
    }
  }
 else {
    boolean isLocalVideo=false;
    if (!imagesArrLocals.isEmpty() && index >= 0 && index < imagesArrLocals.size()) {
      Object object=imagesArrLocals.get(index);
      if (object instanceof MediaController.PhotoEntry) {
        MediaController.PhotoEntry photoEntry=((MediaController.PhotoEntry)object);
        isLocalVideo=photoEntry.isVideo;
      }
    }
    if (isLocalVideo) {
      photoProgressViews[a].setBackgroundState(3,animated);
    }
 else {
      photoProgressViews[a].setBackgroundState(-1,animated);
    }
  }
}
