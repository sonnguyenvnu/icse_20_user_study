private boolean matches(InetAddress destination){
  if (destination == null)   return false;
  if (isDefault())   return true;
  InetAddress dstNet=NetworkUtilsHelper.getNetworkPart(destination,mDestination.getNetworkPrefixLength());
  return mDestination.getAddress().equals(dstNet);
}
