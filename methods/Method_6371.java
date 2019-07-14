public static int getMediaType(TLRPC.Message message){
  if (message == null) {
    return -1;
  }
  if (message.media instanceof TLRPC.TL_messageMediaPhoto) {
    return MEDIA_PHOTOVIDEO;
  }
 else   if (message.media instanceof TLRPC.TL_messageMediaDocument) {
    if (MessageObject.isVoiceMessage(message) || MessageObject.isRoundVideoMessage(message)) {
      return MEDIA_AUDIO;
    }
 else     if (MessageObject.isVideoMessage(message)) {
      return MEDIA_PHOTOVIDEO;
    }
 else     if (MessageObject.isStickerMessage(message)) {
      return -1;
    }
 else     if (MessageObject.isMusicMessage(message)) {
      return MEDIA_MUSIC;
    }
 else {
      return MEDIA_FILE;
    }
  }
 else   if (!message.entities.isEmpty()) {
    for (int a=0; a < message.entities.size(); a++) {
      TLRPC.MessageEntity entity=message.entities.get(a);
      if (entity instanceof TLRPC.TL_messageEntityUrl || entity instanceof TLRPC.TL_messageEntityTextUrl || entity instanceof TLRPC.TL_messageEntityEmail) {
        return MEDIA_URL;
      }
    }
  }
  return -1;
}
