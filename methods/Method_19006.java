public void updateRange(int index,int count,List<RenderInfo> renderInfos,@Nullable TreeProps treeProps,@Nullable List<?> prevData,@Nullable List<?> nextData){
  addChange(Change.updateRange(index,count,wrapTreePropRenderInfos(renderInfos,treeProps),prevData,nextData));
}
