public static String getKeyForParentObject(Object parentObject){
  if (parentObject instanceof MessageObject) {
    MessageObject messageObject=(MessageObject)parentObject;
    int channelId=messageObject.getChannelId();
    return "message" + messageObject.getRealId() + "_" + channelId;
  }
 else   if (parentObject instanceof TLRPC.Message) {
    TLRPC.Message message=(TLRPC.Message)parentObject;
    int channelId=message.to_id != null ? message.to_id.channel_id : 0;
    return "message" + message.id + "_" + channelId;
  }
 else   if (parentObject instanceof TLRPC.WebPage) {
    TLRPC.WebPage webPage=(TLRPC.WebPage)parentObject;
    return "webpage" + webPage.id;
  }
 else   if (parentObject instanceof TLRPC.User) {
    TLRPC.User user=(TLRPC.User)parentObject;
    return "user" + user.id;
  }
 else   if (parentObject instanceof TLRPC.Chat) {
    TLRPC.Chat chat=(TLRPC.Chat)parentObject;
    return "chat" + chat.id;
  }
 else   if (parentObject instanceof String) {
    String string=(String)parentObject;
    return "str" + string;
  }
 else   if (parentObject instanceof TLRPC.TL_messages_stickerSet) {
    TLRPC.TL_messages_stickerSet stickerSet=(TLRPC.TL_messages_stickerSet)parentObject;
    return "set" + stickerSet.set.id;
  }
 else   if (parentObject instanceof TLRPC.StickerSetCovered) {
    TLRPC.StickerSetCovered stickerSet=(TLRPC.StickerSetCovered)parentObject;
    return "set" + stickerSet.set.id;
  }
 else   if (parentObject instanceof TLRPC.InputStickerSet) {
    TLRPC.InputStickerSet inputStickerSet=(TLRPC.InputStickerSet)parentObject;
    return "set" + inputStickerSet.id;
  }
 else   if (parentObject instanceof TLRPC.TL_wallPaper) {
    TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)parentObject;
    return "wallpaper" + wallPaper.id;
  }
  return parentObject != null ? "" + parentObject : null;
}
