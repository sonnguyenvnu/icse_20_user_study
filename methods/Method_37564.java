public static boolean isSocketAccessAllowed(final int localIp,final int socketIp,final int mask){
  boolean _retVal=false;
  if (socketIp == INT_VALUE_127_0_0_1 || (localIp & mask) == (socketIp & mask)) {
    _retVal=true;
  }
  return _retVal;
}
