@Override public String acquireName(){
  return Optional.ofNullable(appName).orElse(buildDefaultApplicationName());
}
