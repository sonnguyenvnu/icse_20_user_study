private static Iterable<InetSocketAddress> combine(InetSocketAddress address,InetSocketAddress... addresses){
  return ImmutableSet.<InetSocketAddress>builder().add(address).add(addresses).build();
}
