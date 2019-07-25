private boolean matches(String from,String status){
  for (  StateTransition transition : transitions) {
    if (from.equals(transition.getState().getName()) && transition.matches(status)) {
      return true;
    }
  }
  return false;
}
