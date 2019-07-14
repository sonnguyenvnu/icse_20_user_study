private void updateDisappearingMountItems(LayoutState newLayoutState){
  final Map<TransitionId,?> nextMountedTransitionIds=newLayoutState.getTransitionIdMapping();
  for (  TransitionId transitionId : nextMountedTransitionIds.keySet()) {
    final OutputUnitsAffinityGroup<MountItem> disappearingItem=mDisappearingMountItems.remove(transitionId);
    if (disappearingItem != null) {
      endUnmountDisappearingItem(disappearingItem);
    }
  }
}
