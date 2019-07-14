/** 
 * Sorts and removes duplicate items, leaving only the last item from each group of "same" items. Move the remaining items to the beginning of the array.
 * @return Number of deduplicated items at the beginning of the array.
 */
private int sortAndDedup(@NonNull T[] items){
  if (items.length == 0) {
    return 0;
  }
  Arrays.sort(items,mCallback);
  int rangeStart=0;
  int rangeEnd=1;
  for (int i=1; i < items.length; ++i) {
    T currentItem=items[i];
    int compare=mCallback.compare(items[rangeStart],currentItem);
    if (compare == 0) {
      final int sameItemPos=findSameItem(currentItem,items,rangeStart,rangeEnd);
      if (sameItemPos != INVALID_POSITION) {
        items[sameItemPos]=currentItem;
      }
 else {
        if (rangeEnd != i) {
          items[rangeEnd]=currentItem;
        }
        rangeEnd++;
      }
    }
 else {
      if (rangeEnd != i) {
        items[rangeEnd]=currentItem;
      }
      rangeStart=rangeEnd++;
    }
  }
  return rangeEnd;
}
