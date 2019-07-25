@OnTransition(target="BUSY") public void busy(ExtendedState extendedState){
  Object cd=extendedState.getVariables().get(Variables.CD);
  if (cd != null) {
    cdStatus=((Cd)cd).getName();
  }
}
