private boolean includeHeader(String header){
  return !this.ignoredHeaders.contains(header.toLowerCase());
}
