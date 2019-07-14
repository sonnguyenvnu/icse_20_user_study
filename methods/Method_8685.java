@Override public boolean onSingleTapUp(MotionEvent e){
  int currentIndex=delegate.getCurrentIndex();
  ArrayList<ImageLocation> imagesArrLocations=delegate.getImagesArrLocations();
  ArrayList<MessageObject> imagesArr=delegate.getImagesArr();
  ArrayList<TLRPC.PageBlock> pageBlockArr=delegate.getPageBlockArr();
  stopScrolling();
  int count=imagesToDraw.size();
  for (int a=0; a < count; a++) {
    ImageReceiver receiver=imagesToDraw.get(a);
    if (receiver.isInsideImage(e.getX(),e.getY())) {
      int num=receiver.getParam();
      if (num < 0 || num >= currentObjects.size()) {
        return true;
      }
      if (imagesArr != null && !imagesArr.isEmpty()) {
        MessageObject messageObject=(MessageObject)currentObjects.get(num);
        int idx=imagesArr.indexOf(messageObject);
        if (currentIndex == idx) {
          return true;
        }
        moveLineProgress=1.0f;
        animateAllLine=true;
        delegate.setCurrentIndex(idx);
      }
 else       if (pageBlockArr != null && !pageBlockArr.isEmpty()) {
        TLRPC.PageBlock pageBlock=(TLRPC.PageBlock)currentObjects.get(num);
        int idx=pageBlockArr.indexOf(pageBlock);
        if (currentIndex == idx) {
          return true;
        }
        moveLineProgress=1.0f;
        animateAllLine=true;
        delegate.setCurrentIndex(idx);
      }
 else       if (imagesArrLocations != null && !imagesArrLocations.isEmpty()) {
        ImageLocation location=(ImageLocation)currentObjects.get(num);
        int idx=imagesArrLocations.indexOf(location);
        if (currentIndex == idx) {
          return true;
        }
        moveLineProgress=1.0f;
        animateAllLine=true;
        delegate.setCurrentIndex(idx);
      }
      break;
    }
  }
  return false;
}
