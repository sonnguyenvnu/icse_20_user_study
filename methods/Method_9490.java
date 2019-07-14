private void setImageIndex(int index,boolean init){
  if (currentIndex == index || placeProvider == null) {
    return;
  }
  if (!init) {
    if (currentThumb != null) {
      currentThumb.release();
      currentThumb=null;
    }
  }
  currentFileNames[0]=getFileName(index);
  currentFileNames[1]=getFileName(index + 1);
  currentFileNames[2]=getFileName(index - 1);
  placeProvider.willSwitchFromPhoto(currentMessageObject,getFileLocation(currentFileLocation),currentIndex);
  int prevIndex=currentIndex;
  currentIndex=index;
  setIsAboutToSwitchToIndex(currentIndex,init);
  boolean isVideo=false;
  boolean sameImage=false;
  Uri videoPath=null;
  if (!imagesArr.isEmpty()) {
    if (currentIndex < 0 || currentIndex >= imagesArr.size()) {
      closePhoto(false,false);
      return;
    }
    MessageObject newMessageObject=imagesArr.get(currentIndex);
    sameImage=init && currentMessageObject != null && currentMessageObject.getId() == newMessageObject.getId();
    currentMessageObject=newMessageObject;
    isVideo=newMessageObject.isVideo();
    if (sharedMediaType == DataQuery.MEDIA_FILE) {
      if (canZoom=newMessageObject.canPreviewDocument()) {
        menuItem.showSubItem(gallery_menu_save);
        setDoubleTapEnabled(true);
      }
 else {
        menuItem.hideSubItem(gallery_menu_save);
        setDoubleTapEnabled(false);
      }
    }
  }
 else   if (!secureDocuments.isEmpty()) {
    if (index < 0 || index >= secureDocuments.size()) {
      closePhoto(false,false);
      return;
    }
    currentSecureDocument=secureDocuments.get(index);
  }
 else   if (!imagesArrLocations.isEmpty()) {
    if (index < 0 || index >= imagesArrLocations.size()) {
      closePhoto(false,false);
      return;
    }
    ImageLocation old=currentFileLocation;
    ImageLocation newLocation=imagesArrLocations.get(index);
    if (init && old != null && newLocation != null && old.location.local_id == newLocation.location.local_id && old.location.volume_id == newLocation.location.volume_id) {
      sameImage=true;
    }
    currentFileLocation=imagesArrLocations.get(index);
  }
 else   if (!imagesArrLocals.isEmpty()) {
    if (index < 0 || index >= imagesArrLocals.size()) {
      closePhoto(false,false);
      return;
    }
    Object object=imagesArrLocals.get(index);
    if (object instanceof TLRPC.BotInlineResult) {
      TLRPC.BotInlineResult botInlineResult=currentBotInlineResult=((TLRPC.BotInlineResult)object);
      if (botInlineResult.document != null) {
        currentPathObject=FileLoader.getPathToAttach(botInlineResult.document).getAbsolutePath();
        isVideo=MessageObject.isVideoDocument(botInlineResult.document);
      }
 else       if (botInlineResult.photo != null) {
        currentPathObject=FileLoader.getPathToAttach(FileLoader.getClosestPhotoSizeWithSize(botInlineResult.photo.sizes,AndroidUtilities.getPhotoSize())).getAbsolutePath();
      }
 else       if (botInlineResult.content instanceof TLRPC.TL_webDocument) {
        currentPathObject=botInlineResult.content.url;
        isVideo=botInlineResult.type.equals("video");
      }
    }
 else {
      boolean isAnimation=false;
      if (object instanceof MediaController.PhotoEntry) {
        MediaController.PhotoEntry photoEntry=((MediaController.PhotoEntry)object);
        currentPathObject=photoEntry.path;
        isVideo=photoEntry.isVideo;
        videoPath=Uri.fromFile(new File(photoEntry.path));
      }
 else       if (object instanceof MediaController.SearchImage) {
        MediaController.SearchImage searchImage=(MediaController.SearchImage)object;
        currentPathObject=searchImage.getPathToAttach();
      }
    }
  }
  if (currentPlaceObject != null) {
    if (animationInProgress == 0) {
      currentPlaceObject.imageReceiver.setVisible(true,true);
    }
 else {
      showAfterAnimation=currentPlaceObject;
    }
  }
  currentPlaceObject=placeProvider.getPlaceForPhoto(currentMessageObject,getFileLocation(currentFileLocation),currentIndex,false);
  if (currentPlaceObject != null) {
    if (animationInProgress == 0) {
      currentPlaceObject.imageReceiver.setVisible(false,true);
    }
 else {
      hideAfterAnimation=currentPlaceObject;
    }
  }
  if (!sameImage) {
    draggingDown=false;
    translationX=0;
    translationY=0;
    scale=1;
    animateToX=0;
    animateToY=0;
    animateToScale=1;
    animationStartTime=0;
    imageMoveAnimation=null;
    changeModeAnimation=null;
    if (aspectRatioFrameLayout != null) {
      aspectRatioFrameLayout.setVisibility(View.INVISIBLE);
    }
    pinchStartDistance=0;
    pinchStartScale=1;
    pinchCenterX=0;
    pinchCenterY=0;
    pinchStartX=0;
    pinchStartY=0;
    moveStartX=0;
    moveStartY=0;
    zooming=false;
    moving=false;
    doubleTap=false;
    invalidCoords=false;
    canDragDown=true;
    changingPage=false;
    switchImageAfterAnimation=0;
    if (sharedMediaType != DataQuery.MEDIA_FILE) {
      canZoom=!imagesArrLocals.isEmpty() || (currentFileNames[0] != null && photoProgressViews[0].backgroundState != 0);
    }
    updateMinMax(scale);
    releasePlayer(false);
  }
  if (isVideo && videoPath != null) {
    isStreaming=false;
    preparePlayer(videoPath,false,false);
  }
  if (prevIndex == -1) {
    setImages();
    for (int a=0; a < 3; a++) {
      checkProgress(a,false);
    }
  }
 else {
    checkProgress(0,false);
    if (prevIndex > currentIndex) {
      ImageReceiver temp=rightImage;
      rightImage=centerImage;
      centerImage=leftImage;
      leftImage=temp;
      PhotoProgressView tempProgress=photoProgressViews[0];
      photoProgressViews[0]=photoProgressViews[2];
      photoProgressViews[2]=tempProgress;
      setIndexToImage(leftImage,currentIndex - 1);
      checkProgress(1,false);
      checkProgress(2,false);
    }
 else     if (prevIndex < currentIndex) {
      ImageReceiver temp=leftImage;
      leftImage=centerImage;
      centerImage=rightImage;
      rightImage=temp;
      PhotoProgressView tempProgress=photoProgressViews[0];
      photoProgressViews[0]=photoProgressViews[1];
      photoProgressViews[1]=tempProgress;
      setIndexToImage(rightImage,currentIndex + 1);
      checkProgress(1,false);
      checkProgress(2,false);
    }
  }
}
