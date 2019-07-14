private TLObject getFileLocation(int index,int[] size){
  if (index < 0) {
    return null;
  }
  if (!secureDocuments.isEmpty()) {
    if (index >= secureDocuments.size()) {
      return null;
    }
    if (size != null) {
      size[0]=secureDocuments.get(index).secureFile.size;
    }
    return secureDocuments.get(index);
  }
 else   if (!imagesArrLocations.isEmpty()) {
    if (index >= imagesArrLocations.size()) {
      return null;
    }
    if (size != null) {
      size[0]=imagesArrLocationsSizes.get(index);
    }
    return imagesArrLocations.get(index).location;
  }
 else   if (!imagesArr.isEmpty()) {
    if (index >= imagesArr.size()) {
      return null;
    }
    MessageObject message=imagesArr.get(index);
    if (message.messageOwner instanceof TLRPC.TL_messageService) {
      if (message.messageOwner.action instanceof TLRPC.TL_messageActionUserUpdatedPhoto) {
        return message.messageOwner.action.newUserPhoto.photo_big;
      }
 else {
        TLRPC.PhotoSize sizeFull=FileLoader.getClosestPhotoSizeWithSize(message.photoThumbs,AndroidUtilities.getPhotoSize());
        if (sizeFull != null) {
          if (size != null) {
            size[0]=sizeFull.size;
            if (size[0] == 0) {
              size[0]=-1;
            }
          }
          return sizeFull;
        }
 else         if (size != null) {
          size[0]=-1;
        }
      }
    }
 else     if (message.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto && message.messageOwner.media.photo != null || message.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && message.messageOwner.media.webpage != null) {
      TLRPC.FileLocation location;
      TLRPC.PhotoSize sizeFull=FileLoader.getClosestPhotoSizeWithSize(message.photoThumbs,AndroidUtilities.getPhotoSize());
      if (sizeFull != null) {
        if (size != null) {
          size[0]=sizeFull.size;
          if (size[0] == 0) {
            size[0]=-1;
          }
        }
        return sizeFull;
      }
 else       if (size != null) {
        size[0]=-1;
      }
    }
 else     if (message.messageOwner.media instanceof TLRPC.TL_messageMediaInvoice) {
      return ((TLRPC.TL_messageMediaInvoice)message.messageOwner.media).photo;
    }
 else     if (message.getDocument() != null && MessageObject.isDocumentHasThumb(message.getDocument())) {
      TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(message.getDocument().thumbs,90);
      if (size != null) {
        size[0]=thumb.size;
        if (size[0] == 0) {
          size[0]=-1;
        }
      }
      return thumb;
    }
  }
  return null;
}
