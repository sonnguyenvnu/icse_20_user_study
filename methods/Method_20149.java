/** 
 * Check which items have had a position changed. Recyclerview does not support batching these.
 */
private void collectMoves(UpdateOpHelper helper){
  Iterator<ModelState> oldItemIterator=oldStateList.iterator();
  ModelState nextOldItem=null;
  for (  ModelState newItem : currentStateList) {
    if (newItem.pair == null) {
      if (helper.moves.isEmpty()) {
        continue;
      }
 else {
        newItem.pairWithSelf();
      }
    }
    if (nextOldItem == null) {
      nextOldItem=getNextItemWithPair(oldItemIterator);
      if (nextOldItem == null) {
        nextOldItem=newItem.pair;
      }
    }
    while (nextOldItem != null) {
      updateItemPosition(newItem.pair,helper.moves);
      updateItemPosition(nextOldItem,helper.moves);
      if (newItem.id == nextOldItem.id && newItem.position == nextOldItem.position) {
        nextOldItem=null;
        break;
      }
      int newItemDistance=newItem.pair.position - newItem.position;
      int oldItemDistance=nextOldItem.pair.position - nextOldItem.position;
      if (newItemDistance == 0 && oldItemDistance == 0) {
        nextOldItem=null;
        break;
      }
      if (oldItemDistance > newItemDistance) {
        helper.move(nextOldItem.position,nextOldItem.pair.position);
        nextOldItem.position=nextOldItem.pair.position;
        nextOldItem.lastMoveOp=helper.getNumMoves();
        nextOldItem=getNextItemWithPair(oldItemIterator);
      }
 else {
        helper.move(newItem.pair.position,newItem.position);
        newItem.pair.position=newItem.position;
        newItem.pair.lastMoveOp=helper.getNumMoves();
        break;
      }
    }
  }
}
