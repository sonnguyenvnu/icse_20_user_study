/** 
 * Called from  {@link #measure(Size,int,int,EventHandler)}. Will only be called if the size specs provided can't be used to measure the view so we need to layout an item to determine the size. TODO T40814333 make this static and return size. Blocker is that children size specs depend on RecyclerBinder params.
 */
private void layoutItemForSize(ComponentTreeHolderRangeInfo holderRangeInfo){
  final ComponentTreeHolder holder=holderRangeInfo.mHolders.get(holderRangeInfo.mPosition);
  final int childWidthSpec=getActualChildrenWidthSpec(holder);
  final int childHeightSpec=getActualChildrenHeightSpec(holder);
  ComponentsSystrace.beginSection("layoutItemForSize");
  try {
    final Size size=new Size();
    holder.computeLayoutSync(mComponentContext,childWidthSpec,childHeightSpec,size);
    mSizeForMeasure=size;
  }
  finally {
    ComponentsSystrace.endSection();
  }
}
