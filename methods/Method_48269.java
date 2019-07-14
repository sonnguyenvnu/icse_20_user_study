@Override public Map<StaticBuffer,EntryList> getSlice(final List<StaticBuffer> keys,final SliceQuery query,final StoreTransaction txh) throws BackendException {
  final Map<StaticBuffer,EntryList> results=new HashMap<>(keys.size());
  final List<StaticBuffer> remainingKeys=new ArrayList<>(keys.size());
  KeySliceQuery[] ksqs=new KeySliceQuery[keys.size()];
  incActionBy(keys.size(),CacheMetricsAction.RETRIEVAL,txh);
  for (int i=0; i < keys.size(); i++) {
    final StaticBuffer key=keys.get(i);
    ksqs[i]=new KeySliceQuery(key,query);
    EntryList result=null;
    if (!isExpired(ksqs[i]))     result=cache.getIfPresent(ksqs[i]);
 else     ksqs[i]=null;
    if (result != null)     results.put(key,result);
 else     remainingKeys.add(key);
  }
  if (!remainingKeys.isEmpty()) {
    incActionBy(remainingKeys.size(),CacheMetricsAction.MISS,txh);
    Map<StaticBuffer,EntryList> subresults=store.getSlice(remainingKeys,query,unwrapTx(txh));
    for (int i=0; i < keys.size(); i++) {
      StaticBuffer key=keys.get(i);
      EntryList subresult=subresults.get(key);
      if (subresult != null) {
        results.put(key,subresult);
        if (ksqs[i] != null)         cache.put(ksqs[i],subresult);
      }
    }
  }
  return results;
}
