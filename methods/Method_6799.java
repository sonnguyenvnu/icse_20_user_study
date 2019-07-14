public boolean canEditMedia(){
  if (isSecretMedia()) {
    return false;
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
    return true;
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
    return !isVoice() && !isSticker() && !isAnimatedSticker() && !isRoundVideo();
  }
  return false;
}
