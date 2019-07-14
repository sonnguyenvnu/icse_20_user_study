private void loadNacosDataIfPresent(final CompositePropertySource composite,final String dataId,final String group,String fileExtension,boolean isRefreshable){
  if (NacosContextRefresher.getRefreshCount() != 0) {
    NacosPropertySource ps;
    if (!isRefreshable) {
      ps=NacosPropertySourceRepository.getNacosPropertySource(dataId);
    }
 else {
      ps=nacosPropertySourceBuilder.build(dataId,group,fileExtension,true);
    }
    composite.addFirstPropertySource(ps);
  }
 else {
    NacosPropertySource ps=nacosPropertySourceBuilder.build(dataId,group,fileExtension,isRefreshable);
    composite.addFirstPropertySource(ps);
  }
}
