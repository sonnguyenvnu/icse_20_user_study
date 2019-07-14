private void fillImages(boolean move,int dx){
  if (!move && !imagesToDraw.isEmpty()) {
    unusedReceivers.addAll(imagesToDraw);
    imagesToDraw.clear();
    moving=false;
    moveLineProgress=1.0f;
    currentItemProgress=1.0f;
    nextItemProgress=0.0f;
  }
  invalidate();
  if (getMeasuredWidth() == 0 || currentPhotos.isEmpty()) {
    return;
  }
  int width=getMeasuredWidth();
  int startX=getMeasuredWidth() / 2 - itemWidth / 2;
  int addRightIndex;
  int addLeftIndex;
  if (move) {
    addRightIndex=Integer.MIN_VALUE;
    addLeftIndex=Integer.MAX_VALUE;
    int count=imagesToDraw.size();
    for (int a=0; a < count; a++) {
      ImageReceiver receiver=imagesToDraw.get(a);
      int num=receiver.getParam();
      int x=startX + (num - currentImage) * (itemWidth + itemSpacing) + dx;
      if (x > width || x + itemWidth < 0) {
        unusedReceivers.add(receiver);
        imagesToDraw.remove(a);
        count--;
        a--;
      }
      addLeftIndex=Math.min(addLeftIndex,num - 1);
      addRightIndex=Math.max(addRightIndex,num + 1);
    }
  }
 else {
    addRightIndex=currentImage;
    addLeftIndex=currentImage - 1;
  }
  if (addRightIndex != Integer.MIN_VALUE) {
    int count=currentPhotos.size();
    for (int a=addRightIndex; a < count; a++) {
      int x=startX + (a - currentImage) * (itemWidth + itemSpacing) + dx;
      if (x < width) {
        ImageLocation location=currentPhotos.get(a);
        ImageReceiver receiver=getFreeReceiver();
        receiver.setImageCoords(x,itemY,itemWidth,itemHeight);
        Object parent;
        if (currentObjects.get(0) instanceof MessageObject) {
          parent=currentObjects.get(a);
        }
 else         if (currentObjects.get(0) instanceof TLRPC.PageBlock) {
          parent=delegate.getParentObject();
        }
 else {
          parent="avatar_" + delegate.getAvatarsDialogId();
        }
        receiver.setImage(null,null,location,"80_80",0,null,parent,1);
        receiver.setParam(a);
      }
 else {
        break;
      }
    }
  }
  if (addLeftIndex != Integer.MAX_VALUE) {
    for (int a=addLeftIndex; a >= 0; a--) {
      int x=startX + (a - currentImage) * (itemWidth + itemSpacing) + dx + itemWidth;
      if (x > 0) {
        ImageLocation location=currentPhotos.get(a);
        ImageReceiver receiver=getFreeReceiver();
        receiver.setImageCoords(x,itemY,itemWidth,itemHeight);
        Object parent;
        if (currentObjects.get(0) instanceof MessageObject) {
          parent=currentObjects.get(a);
        }
 else         if (currentObjects.get(0) instanceof TLRPC.PageBlock) {
          parent=delegate.getParentObject();
        }
 else {
          parent="avatar_" + delegate.getAvatarsDialogId();
        }
        receiver.setImage(null,null,location,"80_80",0,null,parent,1);
        receiver.setParam(a);
      }
 else {
        break;
      }
    }
  }
}
