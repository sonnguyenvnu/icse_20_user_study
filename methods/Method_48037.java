@Override public BerkeleyJEKeyValueStore openDatabase(String name) throws BackendException {
  Preconditions.checkNotNull(name);
  if (stores.containsKey(name)) {
    return stores.get(name);
  }
  try {
    DatabaseConfig dbConfig=new DatabaseConfig();
    dbConfig.setReadOnly(false);
    dbConfig.setAllowCreate(true);
    dbConfig.setTransactional(transactional);
    dbConfig.setKeyPrefixing(true);
    if (batchLoading) {
      dbConfig.setDeferredWrite(true);
    }
    Database db=environment.openDatabase(null,name,dbConfig);
    log.debug("Opened database {}",name,new Throwable());
    BerkeleyJEKeyValueStore store=new BerkeleyJEKeyValueStore(name,db,this);
    stores.put(name,store);
    return store;
  }
 catch (  DatabaseException e) {
    throw new PermanentBackendException("Could not open BerkeleyJE data store",e);
  }
}
