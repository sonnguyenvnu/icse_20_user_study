private void updateAfterScroll(){
  int indexChange=0;
  int dx=drawDx;
  if (Math.abs(dx) > itemWidth / 2 + itemSpacing) {
    if (dx > 0) {
      dx-=itemWidth / 2 + itemSpacing;
      indexChange++;
    }
 else {
      dx+=itemWidth / 2 + itemSpacing;
      indexChange--;
    }
    indexChange+=dx / (itemWidth + itemSpacing * 2);
  }
  nextPhotoScrolling=currentImage - indexChange;
  int currentIndex=delegate.getCurrentIndex();
  ArrayList<ImageLocation> imagesArrLocations=delegate.getImagesArrLocations();
  ArrayList<MessageObject> imagesArr=delegate.getImagesArr();
  ArrayList<TLRPC.PageBlock> pageBlockArr=delegate.getPageBlockArr();
  if (currentIndex != nextPhotoScrolling && nextPhotoScrolling >= 0 && nextPhotoScrolling < currentPhotos.size()) {
    Object photo=currentObjects.get(nextPhotoScrolling);
    int nextPhoto=-1;
    if (imagesArr != null && !imagesArr.isEmpty()) {
      MessageObject messageObject=(MessageObject)photo;
      nextPhoto=imagesArr.indexOf(messageObject);
    }
 else     if (pageBlockArr != null && !pageBlockArr.isEmpty()) {
      TLRPC.PageBlock pageBlock=(TLRPC.PageBlock)photo;
      nextPhoto=pageBlockArr.indexOf(pageBlock);
    }
 else     if (imagesArrLocations != null && !imagesArrLocations.isEmpty()) {
      ImageLocation location=(ImageLocation)photo;
      nextPhoto=imagesArrLocations.indexOf(location);
    }
    if (nextPhoto >= 0) {
      ignoreChanges=true;
      delegate.setCurrentIndex(nextPhoto);
    }
  }
  if (!scrolling) {
    scrolling=true;
    stopedScrolling=false;
  }
  fillImages(true,drawDx);
}
