public static boolean containsKey(KeyColumnValueStore store,StaticBuffer key,int maxColumnLength,StoreTransaction txh) throws BackendException {
  final StaticBuffer end;
  if (maxColumnLength > 32) {
    end=BufferUtil.oneBuffer(maxColumnLength);
  }
 else {
    end=END;
  }
  return !store.getSlice(new KeySliceQuery(key,START,end).setLimit(1),txh).isEmpty();
}
