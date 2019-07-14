@Bean public AcmIntegrationProperties acmIntegrationProperties(){
  AcmIntegrationProperties acmIntegrationProperties=new AcmIntegrationProperties();
  String applicationName=environment.getProperty("spring.application.name");
  String applicationGroup=environment.getProperty("spring.application.group");
  Assert.isTrue(!StringUtils.isEmpty(applicationName),"'spring.application.name' must be configured in bootstrap.properties or bootstrap.yml/yaml...");
  acmIntegrationProperties.setApplicationName(applicationName);
  acmIntegrationProperties.setApplicationGroup(applicationGroup);
  acmIntegrationProperties.setActiveProfiles(environment.getActiveProfiles());
  acmIntegrationProperties.setAcmProperties(acmProperties);
  return acmIntegrationProperties;
}
