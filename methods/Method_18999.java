private static Change acquire(@Type int ct,int index,int toIndex,int count,@Nullable RenderInfo renderInfo,@Nullable List<RenderInfo> renderInfos,@Nullable List<?> prevData,@Nullable List<?> nextData){
  return new Change(ct,index,toIndex,count,renderInfo,renderInfos,prevData,nextData);
}
