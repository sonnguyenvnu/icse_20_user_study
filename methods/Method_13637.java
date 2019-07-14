@Override public PropertySource<?> locate(Environment environment){
  CompositePropertySource compositePropertySource=new CompositePropertySource(DIAMOND_PROPERTY_SOURCE_NAME);
  acmIntegrationProperties.setActiveProfiles(environment.getActiveProfiles());
  for (  String dataId : acmIntegrationProperties.getGroupConfigurationDataIds()) {
    loadDiamondDataIfPresent(compositePropertySource,dataId,acmIntegrationProperties.getAcmProperties().getGroup(),true);
  }
  for (  String dataId : acmIntegrationProperties.getApplicationConfigurationDataIds()) {
    loadDiamondDataIfPresent(compositePropertySource,dataId,acmIntegrationProperties.getAcmProperties().getGroup(),false);
  }
  return compositePropertySource;
}
