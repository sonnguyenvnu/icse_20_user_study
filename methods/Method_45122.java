private boolean isRequestHeader(final Map.Entry<String,String> entry){
  return !IGNORED_REQUEST_HEADERS.contains(entry.getKey());
}
