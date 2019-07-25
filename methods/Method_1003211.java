@Override public void configure(ConfigProperties configProperties){
  roleNameFormat=configProperties.getStringValue("roleNameFormat",roleNameFormat);
}
