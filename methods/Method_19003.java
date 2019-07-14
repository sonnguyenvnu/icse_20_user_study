public void insertRange(int index,int count,List<RenderInfo> renderInfos,@Nullable TreeProps treeProps,@Nullable List<?> data){
  if (mSection != null) {
    for (int i=0, size=renderInfos.size(); i < size; i++) {
      renderInfos.get(i).addDebugInfo(SectionsDebugParams.SECTION_GLOBAL_KEY,mSection.getGlobalKey());
    }
  }
  addChange(Change.insertRange(index,count,wrapTreePropRenderInfos(renderInfos,treeProps),data));
}
