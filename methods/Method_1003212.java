@Override public void configure(ConfigProperties configProperties){
  appName=configProperties.getStringValue("appName",appName);
}
