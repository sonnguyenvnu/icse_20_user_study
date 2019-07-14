private LayoutState calculateLayoutStateInternal(ComponentContext context,Component root,int widthSpec,int heightSpec,boolean diffingEnabled,@Nullable LayoutState previousLayoutState,@Nullable TreeProps treeProps,@CalculateLayoutSource int source,@Nullable String extraAttribution,@Nullable LayoutStateFuture layoutStateFuture){
  final ComponentContext contextWithStateHandler=getNewContextForLayout(context,treeProps,layoutStateFuture);
  return LayoutState.calculate(contextWithStateHandler,root,mId,widthSpec,heightSpec,diffingEnabled,previousLayoutState,source,extraAttribution);
}
