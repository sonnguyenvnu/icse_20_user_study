@Override public void delete(int index){
  if (mUseBackgroundChangeSets) {
    mRecyclerBinder.removeItemAtAsync(index);
  }
 else {
    mRecyclerBinder.removeItemAt(index);
  }
}
