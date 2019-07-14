private boolean isDefault(){
  boolean val=false;
  if (mGateway != null) {
    if (mGateway instanceof Inet4Address) {
      val=(mDestination == null || mDestination.getNetworkPrefixLength() == 0);
    }
 else {
      val=(mDestination == null || mDestination.getNetworkPrefixLength() == 0);
    }
  }
  return val;
}
