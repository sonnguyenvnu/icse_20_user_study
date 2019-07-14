public boolean isWebpageDocument(){
  return messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && messageOwner.media.webpage.document != null && !isGifDocument(messageOwner.media.webpage.document);
}
