/** 
 * @return True if updates should be processed.
 */
boolean onItemRangeRemoved(int positionStart,int itemCount){
  if (itemCount < 1) {
    return false;
  }
  mPendingUpdates.add(obtainUpdateOp(UpdateOp.REMOVE,positionStart,itemCount,null));
  mExistingUpdateTypes|=UpdateOp.REMOVE;
  return mPendingUpdates.size() == 1;
}
