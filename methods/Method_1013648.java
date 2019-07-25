public static List<InetSocketAddress> parse(String addressDesc){
  List<HostPort> hostPorts=parseAsHostPorts(addressDesc);
  List<InetSocketAddress> result=new LinkedList<>();
  hostPorts.forEach((hostPort) -> {
    result.add(new InetSocketAddress(hostPort.getHost(),hostPort.getPort()));
  }
);
  return result;
}
