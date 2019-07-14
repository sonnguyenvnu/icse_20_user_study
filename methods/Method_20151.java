/** 
 * Gets the next item in the list that has a pair, meaning it wasn't inserted or removed.
 */
@Nullable private ModelState getNextItemWithPair(Iterator<ModelState> iterator){
  ModelState nextItem=null;
  while (nextItem == null && iterator.hasNext()) {
    nextItem=iterator.next();
    if (nextItem.pair == null) {
      nextItem=null;
    }
  }
  return nextItem;
}
