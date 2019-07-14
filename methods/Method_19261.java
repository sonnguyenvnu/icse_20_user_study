@Override public int computeWrappedHeight(int maxHeight,List<ComponentTreeHolder> componentTreeHolders){
  return LayoutInfoUtils.computeLinearLayoutWrappedHeight(mLinearLayoutManager,maxHeight,componentTreeHolders);
}
