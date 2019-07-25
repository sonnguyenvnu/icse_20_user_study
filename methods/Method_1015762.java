public static long size(Address[] addrs){
  int retval=Global.SHORT_SIZE;
  if (addrs != null)   for (  Address addr : addrs)   retval+=Util.size(addr);
  return retval;
}
