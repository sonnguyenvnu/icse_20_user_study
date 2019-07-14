@Override public EventTraceDataSourceConfiguration load(final String name){
  GlobalConfiguration configs=loadGlobal();
  EventTraceDataSourceConfiguration result=find(name,configs.getEventTraceDataSourceConfigurations());
  setActivated(configs,result);
  return result;
}
