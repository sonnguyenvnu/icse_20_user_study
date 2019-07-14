@Override public boolean start(){
  if (namingService == null) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("Nacos client should be initialized before starting.");
    }
    return false;
  }
  return true;
}
