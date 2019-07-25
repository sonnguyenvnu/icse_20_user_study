public PingData server(boolean c){
  if (c)   flags=Util.setFlag(flags,is_server);
 else   flags=Util.clearFlags(flags,is_server);
  return this;
}
