@Override public boolean add(final EventTraceDataSourceConfiguration config){
  GlobalConfiguration configs=loadGlobal();
  boolean result=configs.getEventTraceDataSourceConfigurations().getEventTraceDataSourceConfiguration().add(config);
  if (result) {
    configurationsXmlRepository.save(configs);
  }
  return result;
}
