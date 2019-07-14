private boolean maybeUnregisterFromScrap(int index){
  if (mScrapDelegates != null) {
    final int valueIndex=mScrapDelegates.indexOfKey(index);
    if (valueIndex >= 0) {
      final InnerTouchDelegate touchDelegate=mScrapDelegates.valueAt(valueIndex);
      mScrapDelegates.removeAt(valueIndex);
      touchDelegate.release();
      return true;
    }
  }
  return false;
}
