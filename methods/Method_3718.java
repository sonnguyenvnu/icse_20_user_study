/** 
 * @return True if updates should be processed.
 */
boolean onItemRangeInserted(int positionStart,int itemCount){
  if (itemCount < 1) {
    return false;
  }
  mPendingUpdates.add(obtainUpdateOp(UpdateOp.ADD,positionStart,itemCount,null));
  mExistingUpdateTypes|=UpdateOp.ADD;
  return mPendingUpdates.size() == 1;
}
