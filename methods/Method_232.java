private boolean hasViewLocal(){
  for (  ViewBinding bindings : viewBindings) {
    if (bindings.requiresLocal()) {
      return true;
    }
  }
  return false;
}
