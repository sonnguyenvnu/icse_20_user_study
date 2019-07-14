@Override protected void handleEvent(ApplicationEnvironmentPreparedEvent event){
  EdasChangeOrderConfiguration edasChangeOrderConfiguration=EdasChangeOrderConfigurationFactory.getEdasChangeOrderConfiguration();
  logger.info("Sentinel Nacos datasource will" + (edasChangeOrderConfiguration.isEdasManaged() ? " be " : " not be ") + "changed by edas change order.");
  if (!edasChangeOrderConfiguration.isEdasManaged()) {
    return;
  }
  System.getProperties().setProperty(Constants.Sentinel.NACOS_DATASOURCE_ENDPOINT,edasChangeOrderConfiguration.getAddressServerDomain());
  System.getProperties().setProperty(Constants.Sentinel.NACOS_DATASOURCE_NAMESPACE,edasChangeOrderConfiguration.getTenantId());
  System.getProperties().setProperty(Constants.Sentinel.NACOS_DATASOURCE_AK,edasChangeOrderConfiguration.getDauthAccessKey());
  System.getProperties().setProperty(Constants.Sentinel.NACOS_DATASOURCE_SK,edasChangeOrderConfiguration.getDauthSecretKey());
  System.getProperties().setProperty(Constants.Sentinel.PROJECT_NAME,edasChangeOrderConfiguration.getProjectName());
}
