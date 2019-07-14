private Map<StaticBuffer,EntryList> getHelper(List<StaticBuffer> keys,Filter getFilter) throws BackendException {
  List<Get> requests=new ArrayList<>(keys.size());
{
    for (    StaticBuffer key : keys) {
      Get g=new Get(key.as(StaticBuffer.ARRAY_FACTORY)).addFamily(columnFamilyBytes).setFilter(getFilter);
      try {
        g.setTimeRange(0,Long.MAX_VALUE);
      }
 catch (      IOException e) {
        throw new PermanentBackendException(e);
      }
      requests.add(g);
    }
  }
  final Map<StaticBuffer,EntryList> resultMap=new HashMap<>(keys.size());
  try {
    TableMask table=null;
    final Result[] results;
    try {
      table=cnx.getTable(tableName);
      results=table.get(requests);
    }
  finally {
      IOUtils.closeQuietly(table);
    }
    if (results == null)     return KCVSUtil.emptyResults(keys);
    assert results.length == keys.size();
    for (int i=0; i < results.length; i++) {
      final Result result=results[i];
      NavigableMap<byte[],NavigableMap<byte[],NavigableMap<Long,byte[]>>> f=result.getMap();
      if (f == null) {
        resultMap.put(keys.get(i),EntryList.EMPTY_LIST);
        continue;
      }
      NavigableMap<byte[],NavigableMap<Long,byte[]>> r=f.get(columnFamilyBytes);
      resultMap.put(keys.get(i),(r == null) ? EntryList.EMPTY_LIST : StaticArrayEntryList.ofBytes(r.entrySet(),entryGetter));
    }
    return resultMap;
  }
 catch (  InterruptedIOException e) {
    Thread.currentThread().interrupt();
    throw new PermanentBackendException(e);
  }
catch (  IOException e) {
    throw new TemporaryBackendException(e);
  }
}
