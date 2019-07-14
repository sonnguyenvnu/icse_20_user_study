@Override public void deleteRange(int index,int count){
  if (mUseBackgroundChangeSets) {
    mRecyclerBinder.removeRangeAtAsync(index,count);
  }
 else {
    mRecyclerBinder.removeRangeAt(index,count);
  }
}
