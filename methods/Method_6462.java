public static File getPathToMessage(TLRPC.Message message){
  if (message == null) {
    return new File("");
  }
  if (message instanceof TLRPC.TL_messageService) {
    if (message.action.photo != null) {
      ArrayList<TLRPC.PhotoSize> sizes=message.action.photo.sizes;
      if (sizes.size() > 0) {
        TLRPC.PhotoSize sizeFull=getClosestPhotoSizeWithSize(sizes,AndroidUtilities.getPhotoSize());
        if (sizeFull != null) {
          return getPathToAttach(sizeFull);
        }
      }
    }
  }
 else {
    if (message.media instanceof TLRPC.TL_messageMediaDocument) {
      return getPathToAttach(message.media.document,message.media.ttl_seconds != 0);
    }
 else     if (message.media instanceof TLRPC.TL_messageMediaPhoto) {
      ArrayList<TLRPC.PhotoSize> sizes=message.media.photo.sizes;
      if (sizes.size() > 0) {
        TLRPC.PhotoSize sizeFull=getClosestPhotoSizeWithSize(sizes,AndroidUtilities.getPhotoSize());
        if (sizeFull != null) {
          return getPathToAttach(sizeFull,message.media.ttl_seconds != 0);
        }
      }
    }
 else     if (message.media instanceof TLRPC.TL_messageMediaWebPage) {
      if (message.media.webpage.document != null) {
        return getPathToAttach(message.media.webpage.document);
      }
 else       if (message.media.webpage.photo != null) {
        ArrayList<TLRPC.PhotoSize> sizes=message.media.webpage.photo.sizes;
        if (sizes.size() > 0) {
          TLRPC.PhotoSize sizeFull=getClosestPhotoSizeWithSize(sizes,AndroidUtilities.getPhotoSize());
          if (sizeFull != null) {
            return getPathToAttach(sizeFull);
          }
        }
      }
    }
 else     if (message.media instanceof TLRPC.TL_messageMediaInvoice) {
      return getPathToAttach(((TLRPC.TL_messageMediaInvoice)message.media).photo,true);
    }
  }
  return new File("");
}
