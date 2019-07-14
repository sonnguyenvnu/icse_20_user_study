private void regenerateAnimationLockedIndices(LayoutState newLayoutState){
  final Map<TransitionId,OutputUnitsAffinityGroup<LayoutOutput>> transitionMapping=newLayoutState.getTransitionIdMapping();
  if (transitionMapping != null) {
    for (    TransitionId transitionId : transitionMapping.keySet()) {
      if (!mAnimatingTransitionIds.contains(transitionId)) {
        continue;
      }
      if (mAnimationLockedIndices == null) {
        mAnimationLockedIndices=new int[newLayoutState.getMountableOutputCount()];
      }
      final OutputUnitsAffinityGroup<LayoutOutput> group=transitionMapping.get(transitionId);
      for (int j=0, sz=group.size(); j < sz; j++) {
        final LayoutOutput layoutOutput=group.getAt(j);
        final int position=newLayoutState.getLayoutOutputPositionForId(layoutOutput.getId());
        updateAnimationLockCount(newLayoutState,position,true);
      }
    }
  }
 else {
    mAnimationLockedIndices=null;
  }
  if (AnimationsDebug.ENABLED) {
    AnimationsDebug.debugPrintAnimationLockedIndices(newLayoutState,mAnimationLockedIndices);
  }
}
