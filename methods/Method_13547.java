private void loadExtConfiguration(CompositePropertySource compositePropertySource){
  if (nacosConfigProperties.getExtConfig() == null || nacosConfigProperties.getExtConfig().isEmpty()) {
    return;
  }
  List<NacosConfigProperties.Config> extConfigs=nacosConfigProperties.getExtConfig();
  checkExtConfiguration(extConfigs);
  for (  NacosConfigProperties.Config config : extConfigs) {
    String dataId=config.getDataId();
    String fileExtension=dataId.substring(dataId.lastIndexOf(".") + 1);
    loadNacosDataIfPresent(compositePropertySource,dataId,config.getGroup(),fileExtension,config.isRefresh());
  }
}
