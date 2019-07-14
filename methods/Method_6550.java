public static String getStippedKey(Object parentObject,Object fullObject,Object strippedObject){
  if (parentObject instanceof TLRPC.WebPage) {
    if (fullObject instanceof ImageLocation) {
      ImageLocation imageLocation=(ImageLocation)fullObject;
      if (imageLocation.document != null) {
        fullObject=imageLocation.document;
      }
 else       if (imageLocation.photoSize != null) {
        fullObject=imageLocation.photoSize;
      }
 else       if (imageLocation.photo != null) {
        fullObject=imageLocation.photo;
      }
    }
    if (fullObject == null) {
      return "stripped" + FileRefController.getKeyForParentObject(parentObject) + "_" + strippedObject;
    }
 else     if (fullObject instanceof TLRPC.Document) {
      TLRPC.Document document=(TLRPC.Document)fullObject;
      return "stripped" + FileRefController.getKeyForParentObject(parentObject) + "_" + document.id;
    }
 else     if (fullObject instanceof TLRPC.Photo) {
      TLRPC.Photo photo=(TLRPC.Photo)fullObject;
      return "stripped" + FileRefController.getKeyForParentObject(parentObject) + "_" + photo.id;
    }
 else     if (fullObject instanceof TLRPC.PhotoSize) {
      TLRPC.PhotoSize size=(TLRPC.PhotoSize)fullObject;
      if (size.location != null) {
        return "stripped" + FileRefController.getKeyForParentObject(parentObject) + "_" + size.location.local_id + "_" + size.location.volume_id;
      }
 else {
        return "stripped" + FileRefController.getKeyForParentObject(parentObject);
      }
    }
 else     if (fullObject instanceof TLRPC.FileLocation) {
      TLRPC.FileLocation loc=(TLRPC.FileLocation)fullObject;
      return "stripped" + FileRefController.getKeyForParentObject(parentObject) + "_" + loc.local_id + "_" + loc.volume_id;
    }
  }
  return "stripped" + FileRefController.getKeyForParentObject(parentObject);
}
