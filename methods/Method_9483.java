private String getFileName(int index){
  if (index < 0) {
    return null;
  }
  if (!secureDocuments.isEmpty()) {
    if (index >= secureDocuments.size()) {
      return null;
    }
    SecureDocument location=secureDocuments.get(index);
    return location.secureFile.dc_id + "_" + location.secureFile.id + ".jpg";
  }
 else   if (!imagesArrLocations.isEmpty() || !imagesArr.isEmpty()) {
    if (!imagesArrLocations.isEmpty()) {
      if (index >= imagesArrLocations.size()) {
        return null;
      }
      ImageLocation location=imagesArrLocations.get(index);
      if (location == null) {
        return null;
      }
      return location.location.volume_id + "_" + location.location.local_id + ".jpg";
    }
 else     if (!imagesArr.isEmpty()) {
      if (index >= imagesArr.size()) {
        return null;
      }
      return FileLoader.getMessageFileName(imagesArr.get(index).messageOwner);
    }
  }
 else   if (!imagesArrLocals.isEmpty()) {
    if (index >= imagesArrLocals.size()) {
      return null;
    }
    Object object=imagesArrLocals.get(index);
    if (object instanceof MediaController.SearchImage) {
      MediaController.SearchImage searchImage=((MediaController.SearchImage)object);
      return searchImage.getAttachName();
    }
 else     if (object instanceof TLRPC.BotInlineResult) {
      TLRPC.BotInlineResult botInlineResult=(TLRPC.BotInlineResult)object;
      if (botInlineResult.document != null) {
        return FileLoader.getAttachFileName(botInlineResult.document);
      }
 else       if (botInlineResult.photo != null) {
        TLRPC.PhotoSize sizeFull=FileLoader.getClosestPhotoSizeWithSize(botInlineResult.photo.sizes,AndroidUtilities.getPhotoSize());
        return FileLoader.getAttachFileName(sizeFull);
      }
 else       if (botInlineResult.content instanceof TLRPC.TL_webDocument) {
        return Utilities.MD5(botInlineResult.content.url) + "." + ImageLoader.getHttpUrlExtension(botInlineResult.content.url,FileLoader.getMimeTypePart(botInlineResult.content.mime_type));
      }
    }
  }
  return null;
}
