@Override public String getRegionId(){
  if (namespace == null) {
    return null;
  }
  return namespace.contains(":") ? namespace.split(":")[0] : namespace;
}
