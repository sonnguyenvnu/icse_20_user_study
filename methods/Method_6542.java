public static ImageLocation getForObject(TLRPC.PhotoSize photoSize,TLObject object){
  if (object instanceof TLRPC.Photo) {
    return getForPhoto(photoSize,(TLRPC.Photo)object);
  }
 else   if (object instanceof TLRPC.Document) {
    return getForDocument(photoSize,(TLRPC.Document)object);
  }
  return null;
}
