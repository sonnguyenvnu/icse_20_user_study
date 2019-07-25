@Override public synchronized void check(){
  LevelDbDataSourceImpl check=new LevelDbDataSourceImpl(Args.getInstance().getOutputDirectoryByDbName("tmp"),"tmp");
  check.initDB();
  if (!check.allKeys().isEmpty()) {
    Map<String,LevelDbDataSourceImpl> dbMap=dbs.stream().map(db -> Maps.immutableEntry(db.getDBName(),db)).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    for (    Map.Entry<byte[],byte[]> e : check) {
      byte[] key=e.getKey();
      byte[] value=e.getValue();
      String db=simpleDecode(key);
      if (dbMap.get(db) == null) {
        continue;
      }
      byte[] realKey=Arrays.copyOfRange(key,db.getBytes().length + 4,key.length);
      byte[] realValue=value.length == 1 ? null : Arrays.copyOfRange(value,1,value.length);
      if (realValue != null) {
        dbMap.get(db).putData(realKey,realValue,WriteOptionsWrapper.getInstance().sync(Args.getInstance().getStorage().isDbSync()));
      }
 else {
        dbMap.get(db).deleteData(realKey,WriteOptionsWrapper.getInstance().sync(Args.getInstance().getStorage().isDbSync()));
      }
    }
  }
  check.closeDB();
  FileUtil.recursiveDelete(check.getDbPath().toString());
}
