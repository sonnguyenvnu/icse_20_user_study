@StatesOnTransition(target={States.CLOSED,States.IDLE}) public void closed(ExtendedState extendedState){
  Object cd=extendedState.getVariables().get(Variables.CD);
  if (cd != null) {
    cdStatus=((Cd)cd).getName();
  }
 else {
    cdStatus="No CD";
  }
  trackStatus="";
}
