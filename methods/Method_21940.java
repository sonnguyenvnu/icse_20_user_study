private void setActivated(final GlobalConfiguration configs,final EventTraceDataSourceConfiguration toBeConnectedConfig){
  EventTraceDataSourceConfiguration activatedConfig=findActivatedDataSourceConfiguration(configs);
  if (!toBeConnectedConfig.equals(activatedConfig)) {
    if (null != activatedConfig) {
      activatedConfig.setActivated(false);
    }
    toBeConnectedConfig.setActivated(true);
    configurationsXmlRepository.save(configs);
  }
}
