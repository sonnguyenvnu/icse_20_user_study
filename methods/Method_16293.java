static AtomikosDataSourceConfig convert(InDBDynamicDataSourceConfig entity){
  if (null == entity) {
    return null;
  }
  Map<String,Object> config=entity.getProperties();
  if (config == null) {
    return null;
  }
  Properties xaProperties=new Properties();
  for (  Map.Entry<String,Object> entry : config.entrySet()) {
    String key=entry.getKey();
    if (key.startsWith("xaProperties.")) {
      xaProperties.put(key.substring("xaProperties.".length()),entry.getValue());
    }
  }
  AtomikosDataSourceConfig target=FastBeanCopier.copy(config,new AtomikosDataSourceConfig(){
    @Override public int hashCode(){
      return entity.hashCode();
    }
    @Override public boolean equals(    Object o){
      return o instanceof AtomikosDataSourceConfig && hashCode() == o.hashCode();
    }
  }
);
  target.setId(entity.getId());
  target.setName(entity.getName());
  target.setDescribe(entity.getDescribe());
  target.setXaProperties(xaProperties);
  target.setDatabaseType(Optional.ofNullable(config.get("databaseType")).map(String::valueOf).map(DatabaseType::valueOf).orElse(null));
  return target;
}
