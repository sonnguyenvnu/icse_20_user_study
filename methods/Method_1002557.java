private void initialize(RestLiMethodConfig config) throws ResourceMethodConfigParsingException {
  boolean success=initializeProperty(config.getTimeoutMsConfig(),"timeoutMs");
  if (!success) {
    throw new ResourceMethodConfigParsingException("Rest.li resource method level configuration parsing error!");
  }
}
