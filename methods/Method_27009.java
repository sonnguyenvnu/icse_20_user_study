public ReactiveEntityStore<Persistable> getDataStore(){
  if (dataStore == null) {
    EntityModel model=Models.DEFAULT;
    DatabaseSource source=new DatabaseSource(this,model,"FastHub-DB",18);
    Configuration configuration=source.getConfiguration();
    if (BuildConfig.DEBUG) {
      source.setTableCreationMode(TableCreationMode.CREATE_NOT_EXISTS);
    }
    dataStore=ReactiveSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
  }
  return dataStore;
}
