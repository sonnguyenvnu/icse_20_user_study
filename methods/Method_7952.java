public void setVisiblePart(int position,int height){
  if (currentMessageObject == null || currentMessageObject.textLayoutBlocks == null) {
    return;
  }
  position-=textY;
  int newFirst=-1, newLast=-1, newCount=0;
  int startBlock=0;
  for (int a=0; a < currentMessageObject.textLayoutBlocks.size(); a++) {
    if (currentMessageObject.textLayoutBlocks.get(a).textYOffset > position) {
      break;
    }
    startBlock=a;
  }
  for (int a=startBlock; a < currentMessageObject.textLayoutBlocks.size(); a++) {
    MessageObject.TextLayoutBlock block=currentMessageObject.textLayoutBlocks.get(a);
    float y=block.textYOffset;
    if (intersect(y,y + block.height,position,position + height)) {
      if (newFirst == -1) {
        newFirst=a;
      }
      newLast=a;
      newCount++;
    }
 else     if (y > position) {
      break;
    }
  }
  if (lastVisibleBlockNum != newLast || firstVisibleBlockNum != newFirst || totalVisibleBlocksCount != newCount) {
    lastVisibleBlockNum=newLast;
    firstVisibleBlockNum=newFirst;
    totalVisibleBlocksCount=newCount;
    invalidate();
  }
}
