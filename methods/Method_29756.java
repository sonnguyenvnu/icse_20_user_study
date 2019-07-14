@Override protected void onRequestStarted(){
  getListener().onAuthenticateStarted(getRequestCode());
}
