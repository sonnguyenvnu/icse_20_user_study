public boolean isSecretMedia(){
  if (messageOwner instanceof TLRPC.TL_message_secret) {
    return (((messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) || isGif()) && messageOwner.ttl > 0 && messageOwner.ttl <= 60 || isVoice() || isRoundVideo() || isVideo());
  }
 else   if (messageOwner instanceof TLRPC.TL_message) {
    return (messageOwner.media instanceof TLRPC.TL_messageMediaPhoto || messageOwner.media instanceof TLRPC.TL_messageMediaDocument) && messageOwner.media.ttl_seconds != 0;
  }
  return false;
}
