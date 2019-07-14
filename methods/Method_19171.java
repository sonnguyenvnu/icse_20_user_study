@Override public void insertRange(int index,int count,List<RenderInfo> renderInfos){
  if (mUseBackgroundChangeSets) {
    mRecyclerBinder.insertRangeAtAsync(index,renderInfos);
  }
 else {
    mRecyclerBinder.insertRangeAt(index,renderInfos);
  }
}
