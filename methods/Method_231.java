private boolean hasFieldBindings(){
  for (  ViewBinding bindings : viewBindings) {
    if (bindings.getFieldBinding() != null) {
      return true;
    }
  }
  return !collectionBindings.isEmpty();
}
