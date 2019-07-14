private void logUpdateIterative(int index,List<RenderInfo> renderInfos){
  for (int i=0; i < renderInfos.size(); i++) {
    mSectionsDebugLogger.logUpdate(mSectionTreeTag,index + i,renderInfos.get(i),Thread.currentThread().getName());
  }
}
