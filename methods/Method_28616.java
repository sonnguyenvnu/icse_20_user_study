public void clientSetname(final byte[] name){
  sendCommand(CLIENT,Keyword.SETNAME.raw,name);
}
