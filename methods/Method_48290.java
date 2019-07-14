public static Map<StaticBuffer,EntryList> emptyResults(List<StaticBuffer> keys){
  final Map<StaticBuffer,EntryList> result=new HashMap<>(keys.size());
  for (  StaticBuffer key : keys) {
    result.put(key,EntryList.EMPTY_LIST);
  }
  return result;
}
