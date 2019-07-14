private void onPhotoShow(final MessageObject messageObject,final TLRPC.FileLocation fileLocation,final ArrayList<MessageObject> messages,final ArrayList<SecureDocument> documents,final ArrayList<Object> photos,int index,final PlaceProviderObject object){
  classGuid=ConnectionsManager.generateClassGuid();
  currentMessageObject=null;
  currentFileLocation=null;
  currentSecureDocument=null;
  currentPathObject=null;
  fromCamera=false;
  currentBotInlineResult=null;
  currentIndex=-1;
  currentFileNames[0]=null;
  currentFileNames[1]=null;
  currentFileNames[2]=null;
  avatarsDialogId=0;
  totalImagesCount=0;
  totalImagesCountMerge=0;
  currentEditMode=0;
  isFirstLoading=true;
  needSearchImageInArr=false;
  loadingMoreImages=false;
  endReached[0]=false;
  endReached[1]=mergeDialogId == 0;
  opennedFromMedia=false;
  needCaptionLayout=false;
  containerView.setTag(1);
  isCurrentVideo=false;
  imagesArr.clear();
  imagesArrLocations.clear();
  imagesArrLocationsSizes.clear();
  avatarsArr.clear();
  secureDocuments.clear();
  imagesArrLocals.clear();
  for (int a=0; a < 2; a++) {
    imagesByIds[a].clear();
    imagesByIdsTemp[a].clear();
  }
  imagesArrTemp.clear();
  currentUserAvatarLocation=null;
  containerView.setPadding(0,0,0,0);
  if (currentThumb != null) {
    currentThumb.release();
  }
  currentThumb=object != null ? object.thumb : null;
  isEvent=object != null && object.isEvent;
  sharedMediaType=DataQuery.MEDIA_PHOTOVIDEO;
  allMediaItem.setText(LocaleController.getString("ShowAllMedia",R.string.ShowAllMedia));
  menuItem.setVisibility(View.VISIBLE);
  sendItem.setVisibility(View.GONE);
  pipItem.setVisibility(View.GONE);
  cameraItem.setVisibility(View.GONE);
  cameraItem.setTag(null);
  bottomLayout.setVisibility(View.VISIBLE);
  bottomLayout.setTag(1);
  bottomLayout.setTranslationY(0);
  captionTextView.setTranslationY(0);
  shareButton.setVisibility(View.GONE);
  if (qualityChooseView != null) {
    qualityChooseView.setVisibility(View.INVISIBLE);
    qualityPicker.setVisibility(View.INVISIBLE);
    qualityChooseView.setTag(null);
  }
  if (qualityChooseViewAnimation != null) {
    qualityChooseViewAnimation.cancel();
    qualityChooseViewAnimation=null;
  }
  setDoubleTapEnabled(true);
  allowShare=false;
  slideshowMessageId=0;
  nameOverride=null;
  dateOverride=0;
  menuItem.hideSubItem(gallery_menu_showall);
  menuItem.hideSubItem(gallery_menu_showinchat);
  menuItem.hideSubItem(gallery_menu_share);
  menuItem.hideSubItem(gallery_menu_openin);
  actionBar.setTranslationY(0);
  checkImageView.setAlpha(1.0f);
  checkImageView.setVisibility(View.GONE);
  actionBar.setTitleRightMargin(0);
  photosCounterView.setAlpha(1.0f);
  photosCounterView.setVisibility(View.GONE);
  pickerView.setVisibility(View.GONE);
  pickerViewSendButton.setVisibility(View.GONE);
  pickerViewSendButton.setTranslationY(0);
  pickerView.setAlpha(1.0f);
  pickerViewSendButton.setAlpha(1.0f);
  pickerView.setTranslationY(0);
  paintItem.setVisibility(View.GONE);
  cropItem.setVisibility(View.GONE);
  tuneItem.setVisibility(View.GONE);
  timeItem.setVisibility(View.GONE);
  rotateItem.setVisibility(View.GONE);
  videoTimelineView.setVisibility(View.GONE);
  compressItem.setVisibility(View.GONE);
  captionEditText.setVisibility(View.GONE);
  mentionListView.setVisibility(View.GONE);
  muteItem.setVisibility(View.GONE);
  actionBar.setSubtitle(null);
  masksItem.setVisibility(View.GONE);
  muteVideo=false;
  muteItem.setImageResource(R.drawable.volume_on);
  editorDoneLayout.setVisibility(View.GONE);
  captionTextView.setTag(null);
  captionTextView.setVisibility(View.INVISIBLE);
  if (photoCropView != null) {
    photoCropView.setVisibility(View.GONE);
  }
  if (photoFilterView != null) {
    photoFilterView.setVisibility(View.GONE);
  }
  for (int a=0; a < 3; a++) {
    if (photoProgressViews[a] != null) {
      photoProgressViews[a].setBackgroundState(-1,false);
    }
  }
  if (messageObject != null && messages == null) {
    if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && messageObject.messageOwner.media.webpage != null) {
      TLRPC.WebPage webPage=messageObject.messageOwner.media.webpage;
      String siteName=webPage.site_name;
      if (siteName != null) {
        siteName=siteName.toLowerCase();
        if (siteName.equals("instagram") || siteName.equals("twitter") || "telegram_album".equals(webPage.type)) {
          if (!TextUtils.isEmpty(webPage.author)) {
            nameOverride=webPage.author;
          }
          if (webPage.cached_page instanceof TLRPC.TL_page) {
            for (int a=0; a < webPage.cached_page.blocks.size(); a++) {
              TLRPC.PageBlock block=webPage.cached_page.blocks.get(a);
              if (block instanceof TLRPC.TL_pageBlockAuthorDate) {
                dateOverride=((TLRPC.TL_pageBlockAuthorDate)block).published_date;
                break;
              }
            }
          }
          ArrayList<MessageObject> arrayList=messageObject.getWebPagePhotos(null,null);
          if (!arrayList.isEmpty()) {
            slideshowMessageId=messageObject.getId();
            needSearchImageInArr=false;
            imagesArr.addAll(arrayList);
            totalImagesCount=imagesArr.size();
            int idx=imagesArr.indexOf(messageObject);
            if (idx < 0) {
              idx=0;
            }
            setImageIndex(idx,true);
          }
        }
      }
    }
    if (messageObject.canPreviewDocument()) {
      sharedMediaType=DataQuery.MEDIA_FILE;
      allMediaItem.setText(LocaleController.getString("ShowAllFiles",R.string.ShowAllFiles));
    }
    if (slideshowMessageId == 0) {
      imagesArr.add(messageObject);
      if (currentAnimation != null || messageObject.eventId != 0) {
        needSearchImageInArr=false;
      }
 else       if (!(messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaInvoice) && !(messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) && (messageObject.messageOwner.action == null || messageObject.messageOwner.action instanceof TLRPC.TL_messageActionEmpty)) {
        needSearchImageInArr=true;
        imagesByIds[0].put(messageObject.getId(),messageObject);
        menuItem.showSubItem(gallery_menu_showall);
        sendItem.setVisibility(View.VISIBLE);
      }
      setImageIndex(0,true);
    }
  }
 else   if (documents != null) {
    secureDocuments.addAll(documents);
    setImageIndex(index,true);
  }
 else   if (fileLocation != null) {
    avatarsDialogId=object.dialogId;
    ImageLocation imageLocation;
    if (avatarsDialogId > 0) {
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(avatarsDialogId);
      imageLocation=ImageLocation.getForUser(user,true);
    }
 else {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-avatarsDialogId);
      imageLocation=ImageLocation.getForChat(chat,true);
    }
    if (imageLocation == null) {
      closePhoto(false,false);
      return;
    }
    imagesArrLocations.add(imageLocation);
    currentUserAvatarLocation=imageLocation;
    imagesArrLocationsSizes.add(object.size);
    avatarsArr.add(new TLRPC.TL_photoEmpty());
    shareButton.setVisibility(videoPlayerControlFrameLayout.getVisibility() != View.VISIBLE ? View.VISIBLE : View.GONE);
    allowShare=true;
    menuItem.hideSubItem(gallery_menu_showall);
    if (shareButton.getVisibility() == View.VISIBLE) {
      menuItem.hideSubItem(gallery_menu_share);
    }
 else {
      menuItem.showSubItem(gallery_menu_share);
    }
    setImageIndex(0,true);
  }
 else   if (messages != null) {
    opennedFromMedia=true;
    menuItem.showSubItem(gallery_menu_showinchat);
    sendItem.setVisibility(View.VISIBLE);
    imagesArr.addAll(messages);
    for (int a=0; a < imagesArr.size(); a++) {
      MessageObject message=imagesArr.get(a);
      imagesByIds[message.getDialogId() == currentDialogId ? 0 : 1].put(message.getId(),message);
    }
    MessageObject openingObject=imagesArr.get(index);
    if (openingObject.canPreviewDocument()) {
      sharedMediaType=DataQuery.MEDIA_FILE;
      allMediaItem.setText(LocaleController.getString("ShowAllFiles",R.string.ShowAllFiles));
    }
    setImageIndex(index,true);
  }
 else   if (photos != null) {
    if (sendPhotoType == 0 || sendPhotoType == 4 || (sendPhotoType == 2 || sendPhotoType == 5) && photos.size() > 1) {
      checkImageView.setVisibility(View.VISIBLE);
      photosCounterView.setVisibility(View.VISIBLE);
      actionBar.setTitleRightMargin(AndroidUtilities.dp(100));
    }
    if ((sendPhotoType == 2 || sendPhotoType == 5) && placeProvider.canCaptureMorePhotos()) {
      cameraItem.setVisibility(View.VISIBLE);
      cameraItem.setTag(1);
    }
    menuItem.setVisibility(View.GONE);
    imagesArrLocals.addAll(photos);
    Object obj=imagesArrLocals.get(index);
    boolean allowCaption;
    if (obj instanceof MediaController.PhotoEntry) {
      if (((MediaController.PhotoEntry)obj).isVideo) {
        cropItem.setVisibility(View.GONE);
        rotateItem.setVisibility(View.GONE);
        bottomLayout.setVisibility(View.VISIBLE);
        bottomLayout.setTag(1);
        bottomLayout.setTranslationY(-AndroidUtilities.dp(48));
      }
 else {
        cropItem.setVisibility(sendPhotoType != SELECT_TYPE_AVATAR ? View.VISIBLE : View.GONE);
        rotateItem.setVisibility(sendPhotoType != SELECT_TYPE_AVATAR ? View.GONE : View.VISIBLE);
      }
      allowCaption=true;
    }
 else     if (obj instanceof TLRPC.BotInlineResult) {
      cropItem.setVisibility(View.GONE);
      rotateItem.setVisibility(View.GONE);
      allowCaption=false;
    }
 else {
      cropItem.setVisibility(obj instanceof MediaController.SearchImage && ((MediaController.SearchImage)obj).type == 0 ? View.VISIBLE : View.GONE);
      rotateItem.setVisibility(View.GONE);
      allowCaption=cropItem.getVisibility() == View.VISIBLE;
    }
    if (parentChatActivity != null && (parentChatActivity.currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(parentChatActivity.currentEncryptedChat.layer) >= 46)) {
      mentionsAdapter.setChatInfo(parentChatActivity.chatInfo);
      mentionsAdapter.setNeedUsernames(parentChatActivity.currentChat != null);
      mentionsAdapter.setNeedBotContext(false);
      needCaptionLayout=allowCaption && (placeProvider == null || placeProvider != null && placeProvider.allowCaption());
      captionEditText.setVisibility(needCaptionLayout ? View.VISIBLE : View.GONE);
      if (needCaptionLayout) {
        captionEditText.onCreate();
      }
    }
    pickerView.setVisibility(View.VISIBLE);
    pickerViewSendButton.setVisibility(View.VISIBLE);
    pickerViewSendButton.setTranslationY(0);
    pickerViewSendButton.setAlpha(1.0f);
    bottomLayout.setVisibility(View.GONE);
    bottomLayout.setTag(null);
    containerView.setTag(null);
    setImageIndex(index,true);
    if (sendPhotoType == SELECT_TYPE_AVATAR) {
      paintItem.setVisibility(View.VISIBLE);
      tuneItem.setVisibility(View.VISIBLE);
    }
 else     if (sendPhotoType != 4 && sendPhotoType != 5) {
      paintItem.setVisibility(cropItem.getVisibility());
      tuneItem.setVisibility(cropItem.getVisibility());
    }
 else {
      paintItem.setVisibility(View.GONE);
      tuneItem.setVisibility(View.GONE);
    }
    updateSelectedCount();
  }
  if (currentAnimation == null && !isEvent) {
    if (currentDialogId != 0 && totalImagesCount == 0) {
      DataQuery.getInstance(currentAccount).getMediaCount(currentDialogId,sharedMediaType,classGuid,true);
      if (mergeDialogId != 0) {
        DataQuery.getInstance(currentAccount).getMediaCount(mergeDialogId,sharedMediaType,classGuid,true);
      }
    }
 else     if (avatarsDialogId != 0) {
      MessagesController.getInstance(currentAccount).loadDialogPhotos(avatarsDialogId,80,0,true,classGuid);
    }
  }
  if (currentMessageObject != null && currentMessageObject.isVideo() || currentBotInlineResult != null && (currentBotInlineResult.type.equals("video") || MessageObject.isVideoDocument(currentBotInlineResult.document))) {
    onActionClick(false);
  }
 else   if (!imagesArrLocals.isEmpty()) {
    Object entry=imagesArrLocals.get(index);
    CharSequence caption=null;
    TLRPC.User user=parentChatActivity != null ? parentChatActivity.getCurrentUser() : null;
    boolean allowTimeItem=parentChatActivity != null && !parentChatActivity.isSecretChat() && user != null && !user.bot && !parentChatActivity.isEditingMessageMedia();
    if (entry instanceof MediaController.PhotoEntry) {
      MediaController.PhotoEntry photoEntry=((MediaController.PhotoEntry)entry);
      if (photoEntry.isVideo) {
        preparePlayer(Uri.fromFile(new File(photoEntry.path)),false,false);
      }
    }
 else     if (allowTimeItem && entry instanceof MediaController.SearchImage) {
      allowTimeItem=((MediaController.SearchImage)entry).type == 0;
    }
    if (allowTimeItem) {
      timeItem.setVisibility(View.VISIBLE);
    }
  }
}
