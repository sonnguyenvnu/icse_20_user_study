@Override public InetAddress[] resolveInetAddresses(String host) throws UnknownHostException {
  Network network=null;
synchronized (this) {
    if (mNetwork == null) {
      return EMPTY_ADDRESS_ARRAY;
    }
    network=mNetwork;
  }
  return network.getAllByName(host);
}
