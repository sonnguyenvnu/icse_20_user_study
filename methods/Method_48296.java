public static EntryList getSlice(OrderedKeyValueStore store,StaticBuffer keyStart,StaticBuffer keyEnd,StoreTransaction txh) throws BackendException {
  return convert(store.getSlice(new KVQuery(keyStart,keyEnd),txh));
}
