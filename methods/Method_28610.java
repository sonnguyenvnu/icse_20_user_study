public void auth(final String password){
  setPassword(password);
  sendCommand(AUTH,password);
}
