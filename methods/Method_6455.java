public void loadFile(ImageLocation imageLocation,Object parentObject,String ext,int priority,int cacheType){
  if (imageLocation == null) {
    return;
  }
  if (cacheType == 0 && (imageLocation.isEncrypted() || imageLocation.photoSize != null && imageLocation.getSize() == 0)) {
    cacheType=1;
  }
  loadFile(imageLocation.document,imageLocation.secureDocument,imageLocation.webFile,imageLocation.location,imageLocation,parentObject,ext,imageLocation.getSize(),priority,cacheType);
}
