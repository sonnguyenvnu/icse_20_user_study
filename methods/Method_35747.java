public String getPath(){
  return new File(getUri().getSchemeSpecificPart()).getPath();
}
