public void close(){
  for (  Handler handler : newHandlers) {
    logger.removeHandler(handler);
  }
  for (  Handler handler : oldHandlers) {
    logger.addHandler(handler);
  }
  logger.setLevel(oldLogLevel);
  logger.setUseParentHandlers(true);
}
