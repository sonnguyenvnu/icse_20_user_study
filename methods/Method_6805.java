public boolean isWallpaper(){
  return messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && messageOwner.media.webpage != null && "telegram_background".equals(messageOwner.media.webpage.type);
}
