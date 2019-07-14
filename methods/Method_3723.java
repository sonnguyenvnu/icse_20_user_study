boolean hasUpdates(){
  return !mPostponedList.isEmpty() && !mPendingUpdates.isEmpty();
}
