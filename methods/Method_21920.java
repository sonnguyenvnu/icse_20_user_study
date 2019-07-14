private void loadActivatedEventTraceDataSource(final HttpSession httpSession){
  Optional<EventTraceDataSourceConfiguration> config=rdbService.loadActivated();
  if (config.isPresent()) {
    String configName=config.get().getName();
    boolean isConnected=setEventTraceDataSourceNameToSession(rdbService.find(configName,rdbService.loadAll()),httpSession);
    if (isConnected) {
      rdbService.load(configName);
    }
  }
}
