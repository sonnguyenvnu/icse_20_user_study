@Activate protected void activate(Map<String,Object> properties){
  if (properties != null) {
    String corsPropertyValue=(String)properties.get(Constants.CORS_PROPERTY);
    this.isEnabled="true".equalsIgnoreCase(corsPropertyValue);
  }
  if (this.isEnabled) {
    logger.info("enabled CORS for REST API.");
  }
}
