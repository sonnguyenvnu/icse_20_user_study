@Override public void delete(final String name){
  GlobalConfiguration configs=loadGlobal();
  EventTraceDataSourceConfiguration toBeRemovedConfig=find(name,configs.getEventTraceDataSourceConfigurations());
  if (null != toBeRemovedConfig) {
    configs.getEventTraceDataSourceConfigurations().getEventTraceDataSourceConfiguration().remove(toBeRemovedConfig);
    configurationsXmlRepository.save(configs);
  }
}
