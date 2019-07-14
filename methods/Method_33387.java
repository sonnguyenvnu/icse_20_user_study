private boolean isErrorVisible(){
  return isVisible() && errorShowTransition.getStatus().equals(Animation.Status.STOPPED) && errorHideTransition.getStatus().equals(Animation.Status.STOPPED);
}
