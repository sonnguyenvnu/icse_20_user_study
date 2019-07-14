private boolean hasMethodBindings(){
  for (  ViewBinding bindings : viewBindings) {
    if (!bindings.getMethodBindings().isEmpty()) {
      return true;
    }
  }
  return false;
}
