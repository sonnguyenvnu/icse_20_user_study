private GlobalConfiguration loadGlobal(){
  GlobalConfiguration result=configurationsXmlRepository.load();
  if (null == result.getEventTraceDataSourceConfigurations()) {
    result.setEventTraceDataSourceConfigurations(new EventTraceDataSourceConfigurations());
  }
  return result;
}
