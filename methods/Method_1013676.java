@Override public void initialize() throws InitializationException {
  if (apolloDataSourceProviderPresent) {
    try {
      m_delegate=(DataSourceProvider)(Class.forName(apolloDataSourceProviderClass).newInstance());
    }
 catch (    Throwable ex) {
      m_logger.error("Loading apollo datasource provider failed",ex);
    }
  }
  if (m_delegate == null) {
    m_delegate=createDefaultDataSourceProvider();
  }
  if (m_delegate instanceof LogEnabled) {
    ((LogEnabled)m_delegate).enableLogging(m_logger);
  }
  logger.info("[initialize][DataSourceProvider]{}",m_delegate);
}
