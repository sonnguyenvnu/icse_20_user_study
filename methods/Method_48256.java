public static int determineTTL(List<IndexEntry> additions){
  if (additions == null || additions.isEmpty())   return 0;
  int ttl=-1;
  for (  IndexEntry add : additions) {
    int ittl=0;
    if (add.hasMetaData()) {
      Preconditions.checkArgument(add.getMetaData().size() == 1 && add.getMetaData().containsKey(EntryMetaData.TTL),"Index only supports TTL meta data. Found: %s",add.getMetaData());
      ittl=(Integer)add.getMetaData().get(EntryMetaData.TTL);
    }
    if (ttl < 0)     ttl=ittl;
    Preconditions.checkArgument(ttl == ittl,"Index only supports uniform TTL values across all " + "index fields, but got additions: %s",additions);
  }
  assert ttl >= 0;
  return ttl;
}
