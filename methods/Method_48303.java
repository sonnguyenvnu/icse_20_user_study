private static OrderedKeyValueStoreAdapter wrapKeyValueStore(OrderedKeyValueStore store,Map<String,Integer> keyLengths){
  String name=store.getName();
  if (keyLengths.containsKey(name)) {
    int keyLength=keyLengths.get(name);
    Preconditions.checkArgument(keyLength > 0);
    return new OrderedKeyValueStoreAdapter(store,keyLength);
  }
 else {
    return new OrderedKeyValueStoreAdapter(store);
  }
}
