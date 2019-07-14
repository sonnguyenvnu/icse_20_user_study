private boolean setDataSourceNameToSession(final EventTraceDataSourceConfiguration dataSourceConfig,final HttpSession session){
  session.setAttribute(DATA_SOURCE_CONFIG_KEY,dataSourceConfig);
  try {
    EventTraceDataSourceFactory.createEventTraceDataSource(dataSourceConfig.getDriver(),dataSourceConfig.getUrl(),dataSourceConfig.getUsername(),Optional.fromNullable(dataSourceConfig.getPassword()));
    SessionEventTraceDataSourceConfiguration.setDataSourceConfiguration((EventTraceDataSourceConfiguration)session.getAttribute(DATA_SOURCE_CONFIG_KEY));
  }
 catch (  final Exception ex) {
    return false;
  }
  return true;
}
