protected boolean shouldRegister(URL url){
  String side=url.getParameter(SIDE_KEY);
  boolean should=PROVIDER_SIDE.equals(side);
  if (!should) {
    if (logger.isDebugEnabled()) {
      logger.debug("The URL[{}] should not be registered.",url.toString());
    }
  }
  return should;
}
