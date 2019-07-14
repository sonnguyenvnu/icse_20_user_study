public static void applyTTL(Collection<Entry> additions,int ttl){
  for (  Entry entry : additions) {
    assert entry instanceof MetaAnnotatable;
    ((MetaAnnotatable)entry).setMetaData(EntryMetaData.TTL,ttl);
  }
}
