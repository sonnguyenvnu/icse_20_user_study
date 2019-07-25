@Override public Map<Class,Map<String,Record>> dump(){
  Map<String,Record> records=new LinkedHashMap<>();
  for (  Map.Entry<String,RecordJournal> entry : lruCache.asMap().entrySet()) {
    records.put(entry.getKey(),entry.getValue().snapshot);
  }
  Map<Class,Map<String,Record>> dump=new LinkedHashMap<>();
  dump.put(this.getClass(),Collections.unmodifiableMap(records));
  if (nextCache().isPresent()) {
    dump.putAll(nextCache().get().dump());
  }
  return dump;
}
