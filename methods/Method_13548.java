private void checkExtConfiguration(List<NacosConfigProperties.Config> extConfigs){
  String[] dataIds=new String[extConfigs.size()];
  for (int i=0; i < extConfigs.size(); i++) {
    String dataId=extConfigs.get(i).getDataId();
    if (dataId == null || dataId.trim().length() == 0) {
      throw new IllegalStateException(String.format("the [ spring.cloud.nacos.config.ext-config[%s] ] must give a dataid",i));
    }
    dataIds[i]=dataId;
  }
  checkDataIdFileExtension(dataIds);
}
