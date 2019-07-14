public boolean needDrawBluredPreview(){
  if (messageOwner instanceof TLRPC.TL_message_secret) {
    int ttl=Math.max(messageOwner.ttl,messageOwner.media.ttl_seconds);
    return ttl > 0 && ((messageOwner.media instanceof TLRPC.TL_messageMediaPhoto || isVideo() || isGif()) && ttl <= 60 || isRoundVideo());
  }
 else   if (messageOwner instanceof TLRPC.TL_message) {
    return (messageOwner.media instanceof TLRPC.TL_messageMediaPhoto || messageOwner.media instanceof TLRPC.TL_messageMediaDocument) && messageOwner.media.ttl_seconds != 0;
  }
  return false;
}
