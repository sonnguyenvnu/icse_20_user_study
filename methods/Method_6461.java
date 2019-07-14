public static String getMessageFileName(TLRPC.Message message){
  if (message == null) {
    return "";
  }
  if (message instanceof TLRPC.TL_messageService) {
    if (message.action.photo != null) {
      ArrayList<TLRPC.PhotoSize> sizes=message.action.photo.sizes;
      if (sizes.size() > 0) {
        TLRPC.PhotoSize sizeFull=getClosestPhotoSizeWithSize(sizes,AndroidUtilities.getPhotoSize());
        if (sizeFull != null) {
          return getAttachFileName(sizeFull);
        }
      }
    }
  }
 else {
    if (message.media instanceof TLRPC.TL_messageMediaDocument) {
      return getAttachFileName(message.media.document);
    }
 else     if (message.media instanceof TLRPC.TL_messageMediaPhoto) {
      ArrayList<TLRPC.PhotoSize> sizes=message.media.photo.sizes;
      if (sizes.size() > 0) {
        TLRPC.PhotoSize sizeFull=getClosestPhotoSizeWithSize(sizes,AndroidUtilities.getPhotoSize());
        if (sizeFull != null) {
          return getAttachFileName(sizeFull);
        }
      }
    }
 else     if (message.media instanceof TLRPC.TL_messageMediaWebPage) {
      if (message.media.webpage.document != null) {
        return getAttachFileName(message.media.webpage.document);
      }
 else       if (message.media.webpage.photo != null) {
        ArrayList<TLRPC.PhotoSize> sizes=message.media.webpage.photo.sizes;
        if (sizes.size() > 0) {
          TLRPC.PhotoSize sizeFull=getClosestPhotoSizeWithSize(sizes,AndroidUtilities.getPhotoSize());
          if (sizeFull != null) {
            return getAttachFileName(sizeFull);
          }
        }
      }
 else       if (message.media instanceof TLRPC.TL_messageMediaInvoice) {
        return getAttachFileName(((TLRPC.TL_messageMediaInvoice)message.media).photo);
      }
    }
 else     if (message.media instanceof TLRPC.TL_messageMediaInvoice) {
      TLRPC.WebDocument document=((TLRPC.TL_messageMediaInvoice)message.media).photo;
      if (document != null) {
        return Utilities.MD5(document.url) + "." + ImageLoader.getHttpUrlExtension(document.url,getMimeTypePart(document.mime_type));
      }
    }
  }
  return "";
}
