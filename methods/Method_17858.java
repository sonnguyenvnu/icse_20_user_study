@Nullable List<TransitionId> getDisappearingItemTransitionIds(){
  if (!hasDisappearingItems()) {
    return null;
  }
  final List<TransitionId> ids=new ArrayList<>();
  for (int i=0, size=mDisappearingItems.size(); i < size; i++) {
    ids.add(mDisappearingItems.get(i).getTransitionId());
  }
  return ids;
}
