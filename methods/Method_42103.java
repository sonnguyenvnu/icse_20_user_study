public String getDisplayPath(){
  return path != null ? path : getUri().getEncodedPath();
}
