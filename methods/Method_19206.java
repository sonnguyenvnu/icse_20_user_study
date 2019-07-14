public void computeLayoutAsync(ComponentContext context,int widthSpec,int heightSpec,@Nullable MeasureListener measureListener){
  final ComponentTree componentTree;
  final Component component;
  final TreeProps treeProps;
synchronized (this) {
    if (mRenderInfo.rendersView()) {
      return;
    }
    mLastRequestedWidthSpec=widthSpec;
    mLastRequestedHeightSpec=heightSpec;
    ensureComponentTree(context);
    componentTree=mComponentTree;
    component=mRenderInfo.getComponent();
    treeProps=mRenderInfo instanceof TreePropsWrappedRenderInfo ? ((TreePropsWrappedRenderInfo)mRenderInfo).getTreeProps() : null;
  }
  if (measureListener != null) {
    componentTree.updateMeasureListener(measureListener);
  }
  componentTree.setRootAndSizeSpecAsync(component,widthSpec,heightSpec,treeProps);
synchronized (this) {
    if (mComponentTree == componentTree && component == mRenderInfo.getComponent()) {
      mIsTreeValid=true;
    }
  }
}
