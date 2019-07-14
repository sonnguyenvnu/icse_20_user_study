public void applyLazyStateUpdatesForContainer(StateContainer container){
  if (mComponentTree == null) {
    return;
  }
  mComponentTree.applyLazyStateUpdatesForContainer(mComponentScope.getGlobalKey(),container);
}
