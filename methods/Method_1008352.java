@Override public Boolean visit(TypeListenerBinding binding){
  injector.state.addTypeListener(binding);
  return true;
}
