/** 
 * Checks whether it should invalidate span assignments in response to an adapter change.
 */
private void handleUpdate(int positionStart,int itemCountOrToPosition,int cmd){
  int minPosition=mShouldReverseLayout ? getLastChildPosition() : getFirstChildPosition();
  final int affectedRangeEnd;
  final int affectedRangeStart;
  if (cmd == AdapterHelper.UpdateOp.MOVE) {
    if (positionStart < itemCountOrToPosition) {
      affectedRangeEnd=itemCountOrToPosition + 1;
      affectedRangeStart=positionStart;
    }
 else {
      affectedRangeEnd=positionStart + 1;
      affectedRangeStart=itemCountOrToPosition;
    }
  }
 else {
    affectedRangeStart=positionStart;
    affectedRangeEnd=positionStart + itemCountOrToPosition;
  }
  mLazySpanLookup.invalidateAfter(affectedRangeStart);
switch (cmd) {
case AdapterHelper.UpdateOp.ADD:
    mLazySpanLookup.offsetForAddition(positionStart,itemCountOrToPosition);
  break;
case AdapterHelper.UpdateOp.REMOVE:
mLazySpanLookup.offsetForRemoval(positionStart,itemCountOrToPosition);
break;
case AdapterHelper.UpdateOp.MOVE:
mLazySpanLookup.offsetForRemoval(positionStart,1);
mLazySpanLookup.offsetForAddition(itemCountOrToPosition,1);
break;
}
if (affectedRangeEnd <= minPosition) {
return;
}
int maxPosition=mShouldReverseLayout ? getFirstChildPosition() : getLastChildPosition();
if (affectedRangeStart <= maxPosition) {
requestLayout();
}
}
