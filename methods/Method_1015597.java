protected static void unregister(AsciiString channel_name,Address local_addr){
  Map<Address,SHARED_LOOPBACK> map=channel_name != null ? routing_table.get(channel_name) : null;
  if (map != null) {
    map.remove(local_addr);
    if (map.isEmpty())     routing_table.remove(channel_name);
  }
}
