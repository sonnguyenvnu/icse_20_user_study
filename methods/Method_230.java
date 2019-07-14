private boolean hasOnTouchMethodBindings(){
  for (  ViewBinding bindings : viewBindings) {
    if (bindings.getMethodBindings().containsKey(OnTouch.class.getAnnotation(ListenerClass.class))) {
      return true;
    }
  }
  return false;
}
