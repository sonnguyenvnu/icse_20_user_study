@Override public List<Slowlog> slowlogGet(long entries){
  client.slowlogGet(entries);
  return Slowlog.from(client.getObjectMultiBulkReply());
}
