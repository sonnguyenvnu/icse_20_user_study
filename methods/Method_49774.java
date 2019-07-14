private boolean isHost(){
  try {
    return (mGateway.equals(Inet4Address.getLocalHost()) || mGateway.equals(Inet6Address.getLocalHost()));
  }
 catch (  UnknownHostException e) {
    return false;
  }
}
