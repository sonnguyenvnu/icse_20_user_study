@Override public <T>List<T> list(Criteria criteria){
  testAvailable();
  Class clz=criteria.getClz();
  Parsed parsed=Parser.get(clz);
  if (isNoCache()) {
    return dataTransform.list(criteria);
  }
  List<T> list=null;
  List<String> keyList=cacheResolver.getResultKeyList(clz,criteria);
  if (keyList == null || keyList.isEmpty()) {
    list=dataTransform.list(criteria);
    keyList=new ArrayList<>();
    for (    T t : list) {
      String key=getCacheKey(t,parsed);
      keyList.add(key);
    }
    cacheResolver.setResultKeyList(clz,criteria,keyList);
    return list;
  }
  list=cacheResolver.list(clz,keyList);
  if (keyList.size() == list.size())   return list;
  replenishAndRefreshCache(keyList,list,clz,parsed);
  List<T> sortedList=sort(keyList,list,parsed);
  return sortedList;
}
