@Override public void init(final String modelName,final HmilyConfig hmilyConfig){
  keyPrefix=RepositoryPathUtils.buildRedisKeyPrefix(modelName);
  final HmilyRedisConfig hmilyRedisConfig=hmilyConfig.getHmilyRedisConfig();
  try {
    buildJedisPool(hmilyRedisConfig);
  }
 catch (  Exception e) {
    LogUtil.error(LOGGER,"redis init error please check you config:{}",e::getMessage);
    throw new HmilyRuntimeException(e);
  }
}
