@Override public void check(){
  for (  RevokingDBWithCachingNewValue db : dbs) {
    if (!Snapshot.isRoot(db.getHead())) {
      throw new IllegalStateException("first check.");
    }
  }
  if (!checkTmpStore.getDbSource().allKeys().isEmpty()) {
    Map<String,RevokingDBWithCachingNewValue> dbMap=dbs.stream().map(db -> Maps.immutableEntry(db.getDbName(),db)).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    advance();
    for (    Map.Entry<byte[],byte[]> e : checkTmpStore.getDbSource()) {
      byte[] key=e.getKey();
      byte[] value=e.getValue();
      String db=simpleDecode(key);
      if (dbMap.get(db) == null) {
        continue;
      }
      byte[] realKey=Arrays.copyOfRange(key,db.getBytes().length + 4,key.length);
      byte[] realValue=value.length == 1 ? null : Arrays.copyOfRange(value,1,value.length);
      if (realValue != null) {
        dbMap.get(db).getHead().put(realKey,realValue);
      }
 else {
        dbMap.get(db).getHead().remove(realKey);
      }
    }
    dbs.forEach(db -> db.getHead().getRoot().merge(db.getHead()));
    retreat();
  }
  unChecked=false;
}
