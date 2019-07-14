@Override public void init(final String modelName,final HmilyConfig hmilyConfig){
  collectionName=RepositoryPathUtils.buildMongoTableName(modelName);
  final HmilyMongoConfig hmilyMongoConfig=hmilyConfig.getHmilyMongoConfig();
  MongoClientFactoryBean clientFactoryBean=buildMongoClientFactoryBean(hmilyMongoConfig);
  try {
    clientFactoryBean.afterPropertiesSet();
    template=new MongoTemplate(Objects.requireNonNull(clientFactoryBean.getObject()),hmilyMongoConfig.getMongoDbName());
  }
 catch (  Exception e) {
    LogUtil.error(LOGGER,"mongo init error please check you config:{}",e::getMessage);
    throw new HmilyRuntimeException(e);
  }
}
