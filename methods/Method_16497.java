@Override public DataAccessConfig convert(String type,String action,String config){
  if (StringUtils.isEmpty(config)) {
    config="{}";
  }
  SimpleCustomScopeDataAccessConfig accessConfig=JSON.parseObject(config,SimpleCustomScopeDataAccessConfig.class);
  accessConfig.setAction(action);
  return accessConfig;
}
