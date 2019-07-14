@Override public void insertRange(int index,int count,List<RenderInfo> renderInfos){
  dispatchLastEvent();
  mTarget.insertRange(index,count,renderInfos);
  if (ENABLE_LOGGER) {
    logInsertIterative(index,renderInfos);
  }
}
