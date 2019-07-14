/** 
 * Find all insertion operations and add them to the result list. The general strategy here is to walk through the  {@link #currentStateList} and check for items that don't exist in the oldlist. Walking through it in order makes it easy to batch adjacent insertions.
 */
private void collectInsertions(UpdateOpHelper helper){
  Iterator<ModelState> oldItemIterator=oldStateList.iterator();
  for (  ModelState itemToInsert : currentStateList) {
    if (itemToInsert.pair != null) {
      ModelState nextOldItem=getNextItemWithPair(oldItemIterator);
      if (nextOldItem != null) {
        nextOldItem.position+=helper.getNumInsertions();
      }
      continue;
    }
    helper.add(itemToInsert.position);
  }
}
