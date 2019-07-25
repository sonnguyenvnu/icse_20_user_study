@Override public List<InetAddress> lookup(String hostname) throws UnknownHostException {
  if (hostname == null)   throw new UnknownHostException("hostname == null");
  Log.i("CustomGlobalDns","lookup " + hostname);
  try {
    return Arrays.asList(InetAddress.getAllByName(hostname));
  }
 catch (  NullPointerException e) {
    UnknownHostException unknownHostException=new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
    unknownHostException.initCause(e);
    throw unknownHostException;
  }
}
