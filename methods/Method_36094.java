private boolean getFileContextForwardingFlagFrom(ServletConfig config){
  String flagValue=config.getInitParameter(SHOULD_FORWARD_TO_FILES_CONTEXT);
  return Boolean.valueOf(flagValue);
}
