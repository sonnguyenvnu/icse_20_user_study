@Override public final synchronized ComponentTree getComponentForStickyHeaderAt(int position){
  final ComponentTreeHolder holder=mComponentTreeHolders.get(position);
  final int childrenWidthSpec=getActualChildrenWidthSpec(holder);
  final int childrenHeightSpec=getActualChildrenHeightSpec(holder);
  if (holder.isTreeValidForSizeSpecs(childrenWidthSpec,childrenHeightSpec)) {
    return holder.getComponentTree();
  }
  holder.computeLayoutSync(mComponentContext,childrenWidthSpec,childrenHeightSpec,null);
  return holder.getComponentTree();
}
