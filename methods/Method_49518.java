public boolean nextKeyValue() throws IOException {
  final Map<StaticArrayBuffer,Map<StaticBuffer,StaticBuffer>> kv=distinctKeyIterator.next();
  if (kv == null) {
    return false;
  }
  final Map.Entry<StaticArrayBuffer,Map<StaticBuffer,StaticBuffer>> onlyEntry=Iterables.getOnlyElement(kv.entrySet());
  final KV newKV=new KV(onlyEntry.getKey());
  final Map<StaticBuffer,StaticBuffer> v=onlyEntry.getValue();
  final List<Entry> entries=v.keySet().stream().map(column -> StaticArrayEntry.of(column,v.get(column))).collect(toList());
  newKV.addEntries(entries);
  currentKV=newKV;
  return true;
}
