protected static void register(AsciiString channel_name,Address local_addr,SHARED_LOOPBACK shared_loopback){
  Map<Address,SHARED_LOOPBACK> map=routing_table.computeIfAbsent(channel_name,n -> new ConcurrentHashMap<>());
  map.putIfAbsent(local_addr,shared_loopback);
}
