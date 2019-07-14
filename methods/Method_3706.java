void consumePostponedUpdates(){
  final int count=mPostponedList.size();
  for (int i=0; i < count; i++) {
    mCallback.onDispatchSecondPass(mPostponedList.get(i));
  }
  recycleUpdateOpsAndClearList(mPostponedList);
  mExistingUpdateTypes=0;
}
