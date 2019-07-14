@Override protected void handleEvent(ApplicationEnvironmentPreparedEvent event){
  EdasChangeOrderConfiguration edasChangeOrderConfiguration=EdasChangeOrderConfigurationFactory.getEdasChangeOrderConfiguration();
  if (log.isDebugEnabled()) {
    log.debug("Initialize Nacos Discovery Parameter ,is managed {}.",edasChangeOrderConfiguration.isEdasManaged());
  }
  if (!edasChangeOrderConfiguration.isEdasManaged()) {
    return;
  }
  Properties properties=System.getProperties();
  properties.setProperty("spring.cloud.nacos.discovery.server-mode","EDAS");
  properties.setProperty("spring.cloud.nacos.discovery.server-addr","");
  properties.setProperty("spring.cloud.nacos.discovery.endpoint",edasChangeOrderConfiguration.getAddressServerDomain());
  properties.setProperty("spring.cloud.nacos.discovery.namespace",edasChangeOrderConfiguration.getTenantId());
  properties.setProperty("spring.cloud.nacos.discovery.access-key",edasChangeOrderConfiguration.getDauthAccessKey());
  properties.setProperty("spring.cloud.nacos.discovery.secret-key",edasChangeOrderConfiguration.getDauthSecretKey());
  properties.setProperty("nacos.naming.web.context","/vipserver");
  properties.setProperty("nacos.naming.exposed.port","80");
}
