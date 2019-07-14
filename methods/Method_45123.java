private boolean isResponseHeader(final Header header){
  return !IGNORED_RESPONSE_HEADERS.contains(header.getName());
}
