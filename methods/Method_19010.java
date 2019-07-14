/** 
 * Wrap the given list of  {@link RenderInfo} in a {@link TreePropsWrappedRenderInfo}. 
 */
private static List<RenderInfo> wrapTreePropRenderInfos(List<RenderInfo> renderInfos,@Nullable TreeProps treeProps){
  if (treeProps == null) {
    return renderInfos;
  }
  final List<RenderInfo> wrappedRenderInfos=new ArrayList<>(renderInfos.size());
  for (int i=0; i < renderInfos.size(); i++) {
    wrappedRenderInfos.add(new TreePropsWrappedRenderInfo(renderInfos.get(i),treeProps));
  }
  return wrappedRenderInfos;
}
