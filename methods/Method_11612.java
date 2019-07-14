@Override public String getUUID(){
  if (uuid != null) {
    return uuid;
  }
  if (site != null) {
    return site.getDomain();
  }
  uuid=UUID.randomUUID().toString();
  return uuid;
}
