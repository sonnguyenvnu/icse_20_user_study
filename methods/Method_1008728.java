/** 
 * parse a hostname+port range spec into its equivalent addresses 
 */
static TransportAddress[] parse(String hostPortString,String defaultPortRange,int perAddressLimit) throws UnknownHostException {
  Objects.requireNonNull(hostPortString);
  String host;
  String portString=null;
  if (hostPortString.startsWith("[")) {
    Matcher matcher=BRACKET_PATTERN.matcher(hostPortString);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid bracketed host/port range: " + hostPortString);
    }
    host=matcher.group(1);
    portString=matcher.group(2);
  }
 else {
    int colonPos=hostPortString.indexOf(':');
    if (colonPos >= 0 && hostPortString.indexOf(':',colonPos + 1) == -1) {
      host=hostPortString.substring(0,colonPos);
      portString=hostPortString.substring(colonPos + 1);
    }
 else {
      host=hostPortString;
      if (colonPos >= 0) {
        throw new IllegalArgumentException("IPv6 addresses must be bracketed: " + hostPortString);
      }
    }
  }
  if (portString == null || portString.isEmpty()) {
    portString=defaultPortRange;
  }
  Set<InetAddress> addresses=new HashSet<>(Arrays.asList(InetAddress.getAllByName(host)));
  List<TransportAddress> transportAddresses=new ArrayList<>();
  int[] ports=new PortsRange(portString).ports();
  int limit=Math.min(ports.length,perAddressLimit);
  for (int i=0; i < limit; i++) {
    for (    InetAddress address : addresses) {
      transportAddresses.add(new TransportAddress(address,ports[i]));
    }
  }
  return transportAddresses.toArray(new TransportAddress[transportAddresses.size()]);
}
