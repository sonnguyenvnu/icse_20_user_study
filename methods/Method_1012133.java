@Override public void load(Memento memento){
  checkNotRegistered();
  myClassloadPluginId=myPersistencePluginId=memento.get("pluginId");
}
