private void ensureSiteStreamsListenerIsSet(){
  if (getSiteStreamsListeners().length == 0 && getRawStreamListeners().length == 0) {
    throw new IllegalStateException("SiteStreamsListener is not set.");
  }
}
