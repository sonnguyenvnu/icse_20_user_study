final protected void execute() throws RouterException {
  outputMessage=executeSync();
  if (outputMessage != null && getRemoteClientInfo().getExtraResponseHeaders().size() > 0) {
    log.fine("Setting extra headers on response message: " + getRemoteClientInfo().getExtraResponseHeaders().size());
    outputMessage.getHeaders().putAll(getRemoteClientInfo().getExtraResponseHeaders());
  }
}
