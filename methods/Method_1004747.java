@Override public Transport create(Settings settings,SecureSettings secureSettings,String hostInfo){
  if (log.isDebugEnabled()) {
    log.debug("Creating new CommonsHttpTransport");
  }
  return new CommonsHttpTransport(settings,secureSettings,hostInfo);
}
