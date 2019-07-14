protected InDBDynamicDataSourceConfig convert(DataSourceConfigEntity entity){
  if (null == entity) {
    return null;
  }
  Map<String,Object> config=entity.getProperties();
  if (config == null) {
    return null;
  }
  InDBDynamicDataSourceConfig target=FastBeanCopier.copy(config,InDBDynamicDataSourceConfig::new);
  target.setId(entity.getId());
  target.setName(entity.getName());
  target.setDescribe(entity.getDescribe());
  target.setProperties(config);
  return target;
}
