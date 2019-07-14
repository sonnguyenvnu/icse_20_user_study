@Override public void insert(int index,RenderInfo renderInfo){
  if (mUseBackgroundChangeSets) {
    mRecyclerBinder.insertItemAtAsync(index,renderInfo);
  }
 else {
    mRecyclerBinder.insertItemAt(index,renderInfo);
  }
}
