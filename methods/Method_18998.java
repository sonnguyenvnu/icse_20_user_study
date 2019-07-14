private static Change acquireRangedChange(@Type int ct,int index,int count,List<RenderInfo> renderInfos,@Nullable List<?> prevData,@Nullable List<?> nextData){
  return acquire(ct,index,-1,count,null,renderInfos,prevData,nextData);
}
