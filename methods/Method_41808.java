private ToolkitStore createStore(String nameOfMap){
  ToolkitStoreConfigBuilder builder=new ToolkitStoreConfigBuilder();
  return toolkit.getStore(nameOfMap,builder.consistency(Consistency.STRONG).concurrency(1).build(),null);
}
