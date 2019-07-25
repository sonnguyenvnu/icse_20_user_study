public PingData coord(boolean c){
  if (c) {
    flags=Util.setFlag(flags,is_coord);
    flags=Util.setFlag(flags,is_server);
  }
 else   flags=Util.clearFlags(flags,is_coord);
  return this;
}
