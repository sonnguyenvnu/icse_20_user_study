public void computeLayoutSync(ComponentContext context,int widthSpec,int heightSpec,Size size){
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
  componentTree.setRootAndSizeSpec(component,widthSpec,heightSpec,size,treeProps);
synchronized (this) {
    if (componentTree == mComponentTree && component == mRenderInfo.getComponent()) {
      mIsTreeValid=true;
      if (size != null) {
        mLastMeasuredHeight=size.height;
      }
    }
  }
}
