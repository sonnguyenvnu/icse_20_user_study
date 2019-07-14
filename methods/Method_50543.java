@Override public String acquireName(){
  return Optional.ofNullable(basicServiceConfigBean).orElse(build()).getModule();
}
