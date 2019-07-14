@Override public String acquireName(){
  return Optional.ofNullable(applicationConfig).orElse(new ApplicationConfig(buildDefaultApplicationName())).getName();
}
