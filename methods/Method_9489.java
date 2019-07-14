private void setIsAboutToSwitchToIndex(int index,boolean init){
  if (!init && switchingToIndex == index) {
    return;
  }
  switchingToIndex=index;
  boolean isVideo=false;
  boolean sameImage=false;
  CharSequence caption=null;
  String newFileName=getFileName(index);
  MessageObject newMessageObject=null;
  if (!imagesArr.isEmpty()) {
    if (switchingToIndex < 0 || switchingToIndex >= imagesArr.size()) {
      return;
    }
    newMessageObject=imagesArr.get(switchingToIndex);
    isVideo=newMessageObject.isVideo();
    boolean isInvoice=newMessageObject.isInvoice();
    if (isInvoice) {
      masksItem.setVisibility(View.GONE);
      menuItem.hideSubItem(gallery_menu_delete);
      menuItem.hideSubItem(gallery_menu_openin);
      caption=newMessageObject.messageOwner.media.description;
      allowShare=false;
      bottomLayout.setTranslationY(AndroidUtilities.dp(48));
      captionTextView.setTranslationY(AndroidUtilities.dp(48));
    }
 else {
      masksItem.setVisibility(newMessageObject.hasPhotoStickers() && (int)newMessageObject.getDialogId() != 0 ? View.VISIBLE : View.GONE);
      if (newMessageObject.canDeleteMessage(null) && slideshowMessageId == 0) {
        menuItem.showSubItem(gallery_menu_delete);
      }
 else {
        menuItem.hideSubItem(gallery_menu_delete);
      }
      if (isVideo) {
        menuItem.showSubItem(gallery_menu_openin);
        if (pipItem.getVisibility() != View.VISIBLE) {
          pipItem.setVisibility(View.VISIBLE);
        }
        if (!pipAvailable) {
          pipItem.setEnabled(false);
          pipItem.setAlpha(0.5f);
        }
      }
 else {
        menuItem.hideSubItem(gallery_menu_openin);
        if (pipItem.getVisibility() != View.GONE) {
          pipItem.setVisibility(View.GONE);
        }
      }
      if (nameOverride != null) {
        nameTextView.setText(nameOverride);
      }
 else {
        if (newMessageObject.isFromUser()) {
          TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(newMessageObject.messageOwner.from_id);
          if (user != null) {
            nameTextView.setText(UserObject.getUserName(user));
          }
 else {
            nameTextView.setText("");
          }
        }
 else {
          TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(newMessageObject.messageOwner.to_id.channel_id);
          if (ChatObject.isChannel(chat) && chat.megagroup && newMessageObject.isForwardedChannelPost()) {
            chat=MessagesController.getInstance(currentAccount).getChat(newMessageObject.messageOwner.fwd_from.channel_id);
          }
          if (chat != null) {
            nameTextView.setText(chat.title);
          }
 else {
            nameTextView.setText("");
          }
        }
      }
      long date;
      if (dateOverride != 0) {
        date=(long)dateOverride * 1000;
      }
 else {
        date=(long)newMessageObject.messageOwner.date * 1000;
      }
      String dateString=LocaleController.formatString("formatDateAtTime",R.string.formatDateAtTime,LocaleController.getInstance().formatterYear.format(new Date(date)),LocaleController.getInstance().formatterDay.format(new Date(date)));
      if (newFileName != null && isVideo) {
        dateTextView.setText(String.format("%s (%s)",dateString,AndroidUtilities.formatFileSize(newMessageObject.getDocument().size)));
      }
 else {
        dateTextView.setText(dateString);
      }
      caption=newMessageObject.caption;
    }
    if (currentAnimation != null) {
      menuItem.hideSubItem(gallery_menu_save);
      menuItem.hideSubItem(gallery_menu_share);
      if (!newMessageObject.canDeleteMessage(null)) {
        menuItem.setVisibility(View.GONE);
      }
      allowShare=true;
      shareButton.setVisibility(View.VISIBLE);
      actionBar.setTitle(LocaleController.getString("AttachGif",R.string.AttachGif));
    }
 else {
      if (totalImagesCount + totalImagesCountMerge != 0 && !needSearchImageInArr) {
        if (opennedFromMedia) {
          if (imagesArr.size() < totalImagesCount + totalImagesCountMerge && !loadingMoreImages && switchingToIndex > imagesArr.size() - 5) {
            int loadFromMaxId=imagesArr.isEmpty() ? 0 : imagesArr.get(imagesArr.size() - 1).getId();
            int loadIndex=0;
            if (endReached[loadIndex] && mergeDialogId != 0) {
              loadIndex=1;
              if (!imagesArr.isEmpty() && imagesArr.get(imagesArr.size() - 1).getDialogId() != mergeDialogId) {
                loadFromMaxId=0;
              }
            }
            DataQuery.getInstance(currentAccount).loadMedia(loadIndex == 0 ? currentDialogId : mergeDialogId,80,loadFromMaxId,sharedMediaType,1,classGuid);
            loadingMoreImages=true;
          }
          actionBar.setTitle(LocaleController.formatString("Of",R.string.Of,switchingToIndex + 1,totalImagesCount + totalImagesCountMerge));
        }
 else {
          if (imagesArr.size() < totalImagesCount + totalImagesCountMerge && !loadingMoreImages && switchingToIndex < 5) {
            int loadFromMaxId=imagesArr.isEmpty() ? 0 : imagesArr.get(0).getId();
            int loadIndex=0;
            if (endReached[loadIndex] && mergeDialogId != 0) {
              loadIndex=1;
              if (!imagesArr.isEmpty() && imagesArr.get(0).getDialogId() != mergeDialogId) {
                loadFromMaxId=0;
              }
            }
            DataQuery.getInstance(currentAccount).loadMedia(loadIndex == 0 ? currentDialogId : mergeDialogId,80,loadFromMaxId,sharedMediaType,1,classGuid);
            loadingMoreImages=true;
          }
          actionBar.setTitle(LocaleController.formatString("Of",R.string.Of,(totalImagesCount + totalImagesCountMerge - imagesArr.size()) + switchingToIndex + 1,totalImagesCount + totalImagesCountMerge));
        }
      }
 else       if (slideshowMessageId == 0 && newMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) {
        if (newMessageObject.canPreviewDocument()) {
          actionBar.setTitle(LocaleController.getString("AttachDocument",R.string.AttachDocument));
        }
 else         if (newMessageObject.isVideo()) {
          actionBar.setTitle(LocaleController.getString("AttachVideo",R.string.AttachVideo));
        }
 else {
          actionBar.setTitle(LocaleController.getString("AttachPhoto",R.string.AttachPhoto));
        }
      }
 else       if (isInvoice) {
        actionBar.setTitle(newMessageObject.messageOwner.media.title);
      }
 else       if (newMessageObject.isVideo()) {
        actionBar.setTitle(LocaleController.getString("AttachVideo",R.string.AttachVideo));
      }
 else       if (newMessageObject.getDocument() != null) {
        actionBar.setTitle(LocaleController.getString("AttachDocument",R.string.AttachDocument));
      }
      if ((int)currentDialogId == 0) {
        sendItem.setVisibility(View.GONE);
      }
      if (newMessageObject.messageOwner.ttl != 0 && newMessageObject.messageOwner.ttl < 60 * 60) {
        allowShare=false;
        menuItem.hideSubItem(gallery_menu_save);
        shareButton.setVisibility(View.GONE);
        menuItem.hideSubItem(gallery_menu_share);
      }
 else {
        allowShare=true;
        menuItem.showSubItem(gallery_menu_save);
        shareButton.setVisibility(videoPlayerControlFrameLayout.getVisibility() != View.VISIBLE ? View.VISIBLE : View.GONE);
        if (shareButton.getVisibility() == View.VISIBLE) {
          menuItem.hideSubItem(gallery_menu_share);
        }
 else {
          menuItem.showSubItem(gallery_menu_share);
        }
      }
    }
    groupedPhotosListView.fillList();
  }
 else   if (!secureDocuments.isEmpty()) {
    allowShare=false;
    menuItem.hideSubItem(gallery_menu_save);
    nameTextView.setText("");
    dateTextView.setText("");
    actionBar.setTitle(LocaleController.formatString("Of",R.string.Of,switchingToIndex + 1,secureDocuments.size()));
  }
 else   if (!imagesArrLocations.isEmpty()) {
    if (index < 0 || index >= imagesArrLocations.size()) {
      return;
    }
    nameTextView.setText("");
    dateTextView.setText("");
    if (avatarsDialogId == UserConfig.getInstance(currentAccount).getClientUserId() && !avatarsArr.isEmpty()) {
      menuItem.showSubItem(gallery_menu_delete);
    }
 else {
      menuItem.hideSubItem(gallery_menu_delete);
    }
    if (isEvent) {
      actionBar.setTitle(LocaleController.getString("AttachPhoto",R.string.AttachPhoto));
    }
 else {
      actionBar.setTitle(LocaleController.formatString("Of",R.string.Of,switchingToIndex + 1,imagesArrLocations.size()));
    }
    menuItem.showSubItem(gallery_menu_save);
    allowShare=true;
    shareButton.setVisibility(videoPlayerControlFrameLayout.getVisibility() != View.VISIBLE ? View.VISIBLE : View.GONE);
    if (shareButton.getVisibility() == View.VISIBLE) {
      menuItem.hideSubItem(gallery_menu_share);
    }
 else {
      menuItem.showSubItem(gallery_menu_share);
    }
    groupedPhotosListView.fillList();
  }
 else   if (!imagesArrLocals.isEmpty()) {
    if (index < 0 || index >= imagesArrLocals.size()) {
      return;
    }
    Object object=imagesArrLocals.get(index);
    int ttl=0;
    boolean isFiltered=false;
    boolean isPainted=false;
    boolean isCropped=false;
    if (object instanceof TLRPC.BotInlineResult) {
      TLRPC.BotInlineResult botInlineResult=currentBotInlineResult=((TLRPC.BotInlineResult)object);
      if (botInlineResult.document != null) {
        isVideo=MessageObject.isVideoDocument(botInlineResult.document);
      }
 else       if (botInlineResult.content instanceof TLRPC.TL_webDocument) {
        isVideo=botInlineResult.type.equals("video");
      }
    }
 else {
      String pathObject=null;
      boolean isAnimation=false;
      if (object instanceof MediaController.PhotoEntry) {
        MediaController.PhotoEntry photoEntry=((MediaController.PhotoEntry)object);
        pathObject=photoEntry.path;
        isVideo=photoEntry.isVideo;
      }
 else       if (object instanceof MediaController.SearchImage) {
        MediaController.SearchImage searchImage=(MediaController.SearchImage)object;
        pathObject=searchImage.getPathToAttach();
        if (searchImage.type == 1) {
          isAnimation=true;
        }
      }
      if (isVideo) {
        muteItem.setVisibility(View.VISIBLE);
        compressItem.setVisibility(View.VISIBLE);
        isCurrentVideo=true;
        updateAccessibilityOverlayVisibility();
        boolean isMuted=false;
        if (object instanceof MediaController.PhotoEntry) {
          MediaController.PhotoEntry photoEntry=((MediaController.PhotoEntry)object);
          isMuted=photoEntry.editedInfo != null && photoEntry.editedInfo.muted;
        }
        processOpenVideo(pathObject,isMuted);
        videoTimelineView.setVisibility(View.VISIBLE);
        paintItem.setVisibility(View.GONE);
        cropItem.setVisibility(View.GONE);
        tuneItem.setVisibility(View.GONE);
        rotateItem.setVisibility(View.GONE);
      }
 else {
        videoTimelineView.setVisibility(View.GONE);
        muteItem.setVisibility(View.GONE);
        isCurrentVideo=false;
        updateAccessibilityOverlayVisibility();
        compressItem.setVisibility(View.GONE);
        if (isAnimation) {
          paintItem.setVisibility(View.GONE);
          cropItem.setVisibility(View.GONE);
          rotateItem.setVisibility(View.GONE);
          tuneItem.setVisibility(View.GONE);
        }
 else {
          if (sendPhotoType == 4 || sendPhotoType == 5) {
            paintItem.setVisibility(View.GONE);
            tuneItem.setVisibility(View.GONE);
          }
 else {
            paintItem.setVisibility(View.VISIBLE);
            tuneItem.setVisibility(View.VISIBLE);
          }
          cropItem.setVisibility(sendPhotoType != SELECT_TYPE_AVATAR ? View.VISIBLE : View.GONE);
          rotateItem.setVisibility(sendPhotoType != SELECT_TYPE_AVATAR ? View.GONE : View.VISIBLE);
        }
        actionBar.setSubtitle(null);
      }
      if (object instanceof MediaController.PhotoEntry) {
        MediaController.PhotoEntry photoEntry=((MediaController.PhotoEntry)object);
        fromCamera=photoEntry.bucketId == 0 && photoEntry.dateTaken == 0 && imagesArrLocals.size() == 1;
        caption=photoEntry.caption;
        ttl=photoEntry.ttl;
        isFiltered=photoEntry.isFiltered;
        isPainted=photoEntry.isPainted;
        isCropped=photoEntry.isCropped;
      }
 else       if (object instanceof MediaController.SearchImage) {
        MediaController.SearchImage searchImage=(MediaController.SearchImage)object;
        caption=searchImage.caption;
        ttl=searchImage.ttl;
        isFiltered=searchImage.isFiltered;
        isPainted=searchImage.isPainted;
        isCropped=searchImage.isCropped;
      }
    }
    if (bottomLayout.getVisibility() != View.GONE) {
      bottomLayout.setVisibility(View.GONE);
    }
    bottomLayout.setTag(null);
    if (fromCamera) {
      if (isVideo) {
        actionBar.setTitle(LocaleController.getString("AttachVideo",R.string.AttachVideo));
      }
 else {
        actionBar.setTitle(LocaleController.getString("AttachPhoto",R.string.AttachPhoto));
      }
    }
 else {
      actionBar.setTitle(LocaleController.formatString("Of",R.string.Of,switchingToIndex + 1,imagesArrLocals.size()));
    }
    if (parentChatActivity != null) {
      TLRPC.Chat chat=parentChatActivity.getCurrentChat();
      if (chat != null) {
        actionBar.setTitle(chat.title);
      }
 else {
        TLRPC.User user=parentChatActivity.getCurrentUser();
        if (user != null) {
          actionBar.setTitle(ContactsController.formatName(user.first_name,user.last_name));
        }
      }
    }
    if (sendPhotoType == 0 || sendPhotoType == 4 || (sendPhotoType == 2 || sendPhotoType == 5) && imagesArrLocals.size() > 1) {
      checkImageView.setChecked(placeProvider.isPhotoChecked(switchingToIndex),false);
    }
    updateCaptionTextForCurrentPhoto(object);
    PorterDuffColorFilter filter=new PorterDuffColorFilter(0xff3dadee,PorterDuff.Mode.MULTIPLY);
    timeItem.setColorFilter(ttl != 0 ? filter : null);
    paintItem.setColorFilter(isPainted ? filter : null);
    cropItem.setColorFilter(isCropped ? filter : null);
    tuneItem.setColorFilter(isFiltered ? filter : null);
  }
  setCurrentCaption(newMessageObject,caption,!init);
}
