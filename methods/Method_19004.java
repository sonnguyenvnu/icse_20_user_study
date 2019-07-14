public void update(int index,RenderInfo renderInfo,@Nullable TreeProps treeProps,@Nullable Object prevData,@Nullable Object nextData){
  addChange(Change.update(index,new TreePropsWrappedRenderInfo(renderInfo,treeProps),prevData,nextData));
}
