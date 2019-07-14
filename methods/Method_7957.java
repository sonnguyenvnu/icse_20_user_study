private boolean isUserDataChanged(){
  if (currentMessageObject != null && (!hasLinkPreview && currentMessageObject.messageOwner.media != null && currentMessageObject.messageOwner.media.webpage instanceof TLRPC.TL_webPage)) {
    return true;
  }
  if (currentMessageObject == null || currentUser == null && currentChat == null) {
    return false;
  }
  if (lastSendState != currentMessageObject.messageOwner.send_state) {
    return true;
  }
  if (lastDeleteDate != currentMessageObject.messageOwner.destroyTime) {
    return true;
  }
  if (lastViewsCount != currentMessageObject.messageOwner.views) {
    return true;
  }
  updateCurrentUserAndChat();
  TLRPC.FileLocation newPhoto=null;
  if (isAvatarVisible) {
    if (currentUser != null && currentUser.photo != null) {
      newPhoto=currentUser.photo.photo_small;
    }
 else     if (currentChat != null && currentChat.photo != null) {
      newPhoto=currentChat.photo.photo_small;
    }
  }
  if (replyTextLayout == null && currentMessageObject.replyMessageObject != null) {
    return true;
  }
  if (currentPhoto == null && newPhoto != null || currentPhoto != null && newPhoto == null || currentPhoto != null && newPhoto != null && (currentPhoto.local_id != newPhoto.local_id || currentPhoto.volume_id != newPhoto.volume_id)) {
    return true;
  }
  TLRPC.PhotoSize newReplyPhoto=null;
  if (replyNameLayout != null) {
    TLRPC.PhotoSize photoSize=FileLoader.getClosestPhotoSizeWithSize(currentMessageObject.replyMessageObject.photoThumbs,40);
    if (photoSize != null && !currentMessageObject.replyMessageObject.isAnyKindOfSticker()) {
      newReplyPhoto=photoSize;
    }
  }
  if (currentReplyPhoto == null && newReplyPhoto != null) {
    return true;
  }
  String newNameString=null;
  if (drawName && isChat && !currentMessageObject.isOutOwner()) {
    if (currentUser != null) {
      newNameString=UserObject.getUserName(currentUser);
    }
 else     if (currentChat != null) {
      newNameString=currentChat.title;
    }
  }
  if (currentNameString == null && newNameString != null || currentNameString != null && newNameString == null || currentNameString != null && newNameString != null && !currentNameString.equals(newNameString)) {
    return true;
  }
  if (drawForwardedName && currentMessageObject.needDrawForwarded()) {
    newNameString=currentMessageObject.getForwardedName();
    return currentForwardNameString == null && newNameString != null || currentForwardNameString != null && newNameString == null || currentForwardNameString != null && newNameString != null && !currentForwardNameString.equals(newNameString);
  }
  return false;
}
