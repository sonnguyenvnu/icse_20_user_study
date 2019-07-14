public static ImageLocation getForDocument(TLRPC.Document document){
  if (document == null) {
    return null;
  }
  ImageLocation imageLocation=new ImageLocation();
  imageLocation.document=document;
  imageLocation.key=document.key;
  imageLocation.iv=document.iv;
  imageLocation.currentSize=document.size;
  return imageLocation;
}
