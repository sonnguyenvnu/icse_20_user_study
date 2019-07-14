synchronized void updateStateLazy(String key,StateContainer.StateUpdate stateUpdate){
  addStateUpdateInternal(key,stateUpdate,true);
}
