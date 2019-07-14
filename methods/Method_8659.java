private void prepareLayout(float viewPortAvailableSize){
  if (viewPortAvailableSize == 0) {
    viewPortAvailableSize=100;
  }
  itemSpans.clear();
  itemsToRow.clear();
  rowsCount=0;
  firstRowMax=0;
  int preferredRowSize=AndroidUtilities.dp(100);
  int itemsCount=getFlowItemCount();
  int spanCount=getSpanCount();
  int spanLeft=spanCount;
  int currentItemsInRow=0;
  int currentItemsSpanAmount=0;
  for (int a=0; a < itemsCount; a++) {
    Size size=sizeForItem(a);
    int requiredSpan=Math.min(spanCount,(int)Math.floor(spanCount * (size.width / size.height * preferredRowSize / viewPortAvailableSize)));
    boolean moveToNewRow=spanLeft < requiredSpan || requiredSpan > 33 && spanLeft < requiredSpan - 15;
    if (moveToNewRow) {
      if (spanLeft != 0) {
        int spanPerItem=spanLeft / currentItemsInRow;
        for (int start=a - currentItemsInRow, b=start; b < start + currentItemsInRow; b++) {
          if (b == start + currentItemsInRow - 1) {
            itemSpans.put(b,itemSpans.get(b) + spanLeft);
          }
 else {
            itemSpans.put(b,itemSpans.get(b) + spanPerItem);
          }
          spanLeft-=spanPerItem;
        }
        itemsToRow.put(a - 1,rowsCount);
      }
      rowsCount++;
      currentItemsSpanAmount=0;
      currentItemsInRow=0;
      spanLeft=spanCount;
    }
 else {
      if (spanLeft < requiredSpan) {
        requiredSpan=spanLeft;
      }
    }
    if (rowsCount == 0) {
      firstRowMax=Math.max(firstRowMax,a);
    }
    if (a == itemsCount - 1) {
      itemsToRow.put(a,rowsCount);
    }
    currentItemsSpanAmount+=requiredSpan;
    currentItemsInRow++;
    spanLeft-=requiredSpan;
    itemSpans.put(a,requiredSpan);
  }
  if (itemsCount != 0) {
    rowsCount++;
  }
}
