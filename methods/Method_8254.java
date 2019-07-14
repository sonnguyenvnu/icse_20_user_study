private int getScrollOffsetForMessage(MessageObject object){
  int offset=Integer.MAX_VALUE;
  MessageObject.GroupedMessages groupedMessages=getValidGroupedMessage(object);
  if (groupedMessages != null) {
    MessageObject.GroupedMessagePosition currentPosition=groupedMessages.positions.get(object);
    float maxH=Math.max(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.5f;
    float itemHeight;
    if (currentPosition.siblingHeights != null) {
      itemHeight=currentPosition.siblingHeights[0];
    }
 else {
      itemHeight=currentPosition.ph;
    }
    float totalHeight=0.0f;
    float moveDiff=0.0f;
    SparseBooleanArray array=new SparseBooleanArray();
    for (int a=0; a < groupedMessages.posArray.size(); a++) {
      MessageObject.GroupedMessagePosition pos=groupedMessages.posArray.get(a);
      if (array.indexOfKey(pos.minY) < 0 && pos.siblingHeights == null) {
        array.put(pos.minY,true);
        if (pos.minY < currentPosition.minY) {
          moveDiff-=pos.ph;
        }
 else         if (pos.minY > currentPosition.minY) {
          moveDiff+=pos.ph;
        }
        totalHeight+=pos.ph;
      }
    }
    if (Math.abs(totalHeight - itemHeight) < 0.02f) {
      offset=(int)(chatListView.getMeasuredHeight() - totalHeight * maxH) / 2 - chatListView.getPaddingTop() - AndroidUtilities.dp(7);
    }
 else {
      offset=(int)(chatListView.getMeasuredHeight() - (itemHeight + moveDiff) * maxH) / 2 - chatListView.getPaddingTop() - AndroidUtilities.dp(7);
    }
  }
  return Math.max(0,offset == Integer.MAX_VALUE ? (chatListView.getMeasuredHeight() - object.getApproximateHeight()) / 2 : offset);
}
