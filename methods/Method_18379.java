private void startUnmountDisappearingItem(MountItem item,int index){
  final TransitionId transitionId=item.getTransitionId();
  OutputUnitsAffinityGroup<MountItem> disappearingGroup=mDisappearingMountItems.get(transitionId);
  if (disappearingGroup == null) {
    disappearingGroup=new OutputUnitsAffinityGroup<>();
    mDisappearingMountItems.put(transitionId,disappearingGroup);
  }
  final @OutputUnitType int type=LayoutStateOutputIdCalculator.getTypeFromId(mLayoutOutputsIds[index]);
  disappearingGroup.add(type,item);
  final ComponentHost host=item.getHost();
  host.startUnmountDisappearingItem(index,item);
}
