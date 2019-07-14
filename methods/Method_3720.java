/** 
 * @return True if updates should be processed.
 */
boolean onItemRangeMoved(int from,int to,int itemCount){
  if (from == to) {
    return false;
  }
  if (itemCount != 1) {
    throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
  }
  mPendingUpdates.add(obtainUpdateOp(UpdateOp.MOVE,from,to,null));
  mExistingUpdateTypes|=UpdateOp.MOVE;
  return mPendingUpdates.size() == 1;
}
