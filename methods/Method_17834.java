public void updateStateLazy(StateUpdate stateUpdate){
  if (mComponentTree == null) {
    return;
  }
  mComponentTree.updateStateLazy(mComponentScope.getGlobalKey(),stateUpdate);
}
