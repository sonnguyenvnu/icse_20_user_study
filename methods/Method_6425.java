public int canDownloadMedia(TLRPC.Message message){
  if (message == null) {
    return 0;
  }
  int type;
  boolean isVideo;
  if ((isVideo=MessageObject.isVideoMessage(message)) || MessageObject.isGifMessage(message) || MessageObject.isRoundVideoMessage(message) || MessageObject.isGameMessage(message)) {
    type=AUTODOWNLOAD_TYPE_VIDEO;
  }
 else   if (MessageObject.isVoiceMessage(message)) {
    type=AUTODOWNLOAD_TYPE_AUDIO;
  }
 else   if (MessageObject.isPhoto(message) || MessageObject.isStickerMessage(message)) {
    type=AUTODOWNLOAD_TYPE_PHOTO;
  }
 else   if (MessageObject.getDocument(message) != null) {
    type=AUTODOWNLOAD_TYPE_DOCUMENT;
  }
 else {
    return 0;
  }
  int index;
  TLRPC.Peer peer=message.to_id;
  if (peer != null) {
    if (peer.user_id != 0) {
      if (ContactsController.getInstance(currentAccount).contactsDict.containsKey(peer.user_id)) {
        index=0;
      }
 else {
        index=1;
      }
    }
 else     if (peer.chat_id != 0) {
      if (message.from_id != 0 && ContactsController.getInstance(currentAccount).contactsDict.containsKey(message.from_id)) {
        index=0;
      }
 else {
        index=2;
      }
    }
 else {
      if (MessageObject.isMegagroup(message)) {
        if (message.from_id != 0 && ContactsController.getInstance(currentAccount).contactsDict.containsKey(message.from_id)) {
          index=0;
        }
 else {
          index=2;
        }
      }
 else {
        index=3;
      }
    }
  }
 else {
    index=1;
  }
  Preset preset;
  if (ApplicationLoader.isConnectedToWiFi()) {
    if (!wifiPreset.enabled) {
      return 0;
    }
    preset=getCurrentWiFiPreset();
  }
 else   if (ApplicationLoader.isRoaming()) {
    if (!roamingPreset.enabled) {
      return 0;
    }
    preset=getCurrentRoamingPreset();
  }
 else {
    if (!mobilePreset.enabled) {
      return 0;
    }
    preset=getCurrentMobilePreset();
  }
  int mask=preset.mask[index];
  int maxSize=preset.sizes[typeToIndex(type)];
  int size=MessageObject.getMessageSize(message);
  if (isVideo && preset.preloadVideo && size > maxSize && maxSize > 2 * 1024 * 1024) {
    return (mask & type) != 0 ? 2 : 0;
  }
 else {
    return (type == AUTODOWNLOAD_TYPE_PHOTO || size != 0 && size <= maxSize) && (type == AUTODOWNLOAD_TYPE_AUDIO || (mask & type) != 0) ? 1 : 0;
  }
}
