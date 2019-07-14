@Override public DataAccessConfig convert(String type,String action,String config){
  if (StringUtils.isEmpty(config)) {
    config="{}";
  }
  ScopeByUserDataAccessConfig dataAccessConfig=JSON.parseObject(config,ScopeByUserDataAccessConfig.class);
  dataAccessConfig.setAction(action);
  return dataAccessConfig;
}
