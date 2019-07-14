public void configSet(final byte[] parameter,final byte[] value){
  sendCommand(CONFIG,Keyword.SET.raw,parameter,value);
}
