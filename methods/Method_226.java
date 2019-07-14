/** 
 * True when this type's bindings require a view hierarchy. 
 */
private boolean hasViewBindings(){
  return !viewBindings.isEmpty() || !collectionBindings.isEmpty();
}
