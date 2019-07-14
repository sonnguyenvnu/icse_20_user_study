@Override public void init(final String modelName,final HmilyConfig hmilyConfig){
  rootPathPrefix=RepositoryPathUtils.buildZookeeperPathPrefix(modelName);
  try {
    connect(hmilyConfig.getHmilyZookeeperConfig());
  }
 catch (  Exception e) {
    LogUtil.error(LOGGER,"zookeeper init error please check you config:{}",e::getMessage);
    throw new HmilyRuntimeException(e.getMessage());
  }
}
