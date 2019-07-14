@Override public void updateRange(int index,int count,List<RenderInfo> renderInfos){
  if (mUseBackgroundChangeSets) {
    mRecyclerBinder.updateRangeAtAsync(index,renderInfos);
  }
 else {
    mRecyclerBinder.updateRangeAt(index,renderInfos);
  }
}
