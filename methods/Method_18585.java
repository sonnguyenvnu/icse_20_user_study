/** 
 * Unregisters an inner touch delegate with the given index.
 * @param index The drawing order index of the given view.
 */
void unregisterTouchExpansion(int index){
  if (maybeUnregisterFromScrap(index)) {
    return;
  }
  final int valueIndex=mDelegates.indexOfKey(index);
  final InnerTouchDelegate touchDelegate=mDelegates.valueAt(valueIndex);
  mDelegates.removeAt(valueIndex);
  touchDelegate.release();
}
