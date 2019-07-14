public void performIncrementalMount(Rect visibleRect,boolean processVisibilityOutputs){
  if (mComponentTree == null || !checkMainThreadLayoutStateForIncrementalMount()) {
    return;
  }
  if (mComponentTree.isIncrementalMountEnabled()) {
    mComponentTree.mountComponent(visibleRect,processVisibilityOutputs);
  }
 else {
    throw new IllegalStateException("To perform incremental mounting, you need first to enable" + " it when creating the ComponentTree.");
  }
}
