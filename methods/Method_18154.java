@Override public InternalNode reconcile(ComponentContext c,Component next){
  final StateHandler stateHandler=c.getStateHandler();
  final Set<String> keys;
  if (stateHandler == null) {
    keys=Collections.emptySet();
  }
 else {
    keys=stateHandler.getKeysForPendingUpdates();
  }
  return reconcile(c,this,next,keys);
}
