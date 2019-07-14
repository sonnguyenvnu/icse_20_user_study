/** 
 * @return Whether or not to continue layout computation for current range 
 */
private boolean computeRangeLayoutAt(int index,int rangeStart,int rangeEnd,int treeHoldersSize){
  final ComponentTreeHolder holder;
  final int childrenWidthSpec, childrenHeightSpec;
synchronized (this) {
    if (treeHoldersSize != mComponentTreeHolders.size()) {
      return false;
    }
    holder=mComponentTreeHolders.get(index);
    if (holder.getRenderInfo().rendersView()) {
      return true;
    }
    childrenWidthSpec=getActualChildrenWidthSpec(holder);
    childrenHeightSpec=getActualChildrenHeightSpec(holder);
  }
  if (index >= rangeStart && index <= rangeEnd) {
    if (!holder.isTreeValidForSizeSpecs(childrenWidthSpec,childrenHeightSpec)) {
      holder.computeLayoutAsync(mComponentContext,childrenWidthSpec,childrenHeightSpec);
    }
  }
 else {
    if (ThreadUtils.isMainThread()) {
      maybeAcquireStateAndReleaseTree(holder);
    }
 else {
      mMainThreadHandler.post(getMaybeAcquireStateAndReleaseTreeRunnable(holder));
    }
  }
  return true;
}
