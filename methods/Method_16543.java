@Override public DataAccessConfig convert(String type,String action,String config){
  if (StringUtils.isEmpty(config)) {
    config="{}";
  }
  SimpleScopeDataAccessConfig accessConfig=JSON.parseObject(config,SimpleScopeDataAccessConfig.class);
  accessConfig.setAction(action);
  accessConfig.setType(type);
  return accessConfig;
}
