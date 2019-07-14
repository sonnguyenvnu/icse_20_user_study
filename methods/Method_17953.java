/** 
 * If we have transition key on root component we might run bounds animation on LithoView which requires to know animating value in  {@link LithoView#onMeasure(int,int)}. In such case we need to collect all transitions before mount happens but after layout computation is finalized.
 */
void maybeCollectTransitions(){
  assertMainThread();
  final LayoutState layoutState=mMainThreadLayoutState;
  if (layoutState == null || layoutState.getRootTransitionId() == null) {
    return;
  }
  final MountState mountState=mLithoView.getMountState();
  if (mountState.isDirty()) {
    mountState.collectAllTransitions(layoutState,this);
  }
}
