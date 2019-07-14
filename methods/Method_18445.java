private void removeDisappearingMountContentFromComponentHost(ComponentHost componentHost){
  if (componentHost.hasDisappearingItems()) {
    List<TransitionId> ids=componentHost.getDisappearingItemTransitionIds();
    for (int i=0, size=ids.size(); i < size; i++) {
      mTransitionManager.setMountContent(ids.get(i),null);
    }
  }
}
