@Override public Server choose(Object key){
  try {
    String clusterName=this.nacosDiscoveryProperties.getClusterName();
    String targetVersion=this.nacosDiscoveryProperties.getMetadata().get("target-version");
    DynamicServerListLoadBalancer loadBalancer=(DynamicServerListLoadBalancer)getLoadBalancer();
    String name=loadBalancer.getName();
    NamingService namingService=this.nacosDiscoveryProperties.namingServiceInstance();
    List<Instance> instances=namingService.selectInstances(name,true);
    List<Instance> metadataMatchInstances=instances;
    if (StringUtils.isNotBlank(targetVersion)) {
      metadataMatchInstances=instances.stream().filter(instance -> Objects.equals(targetVersion,instance.getMetadata().get("version"))).collect(Collectors.toList());
      if (CollectionUtils.isEmpty(metadataMatchInstances)) {
        log.warn("????????????????????targetVersion = {}, instance = {}",targetVersion,instances);
        return null;
      }
    }
    List<Instance> clusterMetadataMatchInstances=metadataMatchInstances;
    if (StringUtils.isNotBlank(clusterName)) {
      clusterMetadataMatchInstances=metadataMatchInstances.stream().filter(instance -> Objects.equals(clusterName,instance.getClusterName())).collect(Collectors.toList());
      if (CollectionUtils.isEmpty(clusterMetadataMatchInstances)) {
        clusterMetadataMatchInstances=metadataMatchInstances;
        log.warn("????????clusterName = {}, targetVersion = {}, clusterMetadataMatchInstances = {}",clusterName,targetVersion,clusterMetadataMatchInstances);
      }
    }
    Instance instance=ExtendBalancer.getHostByRandomWeight2(clusterMetadataMatchInstances);
    return new NacosServer(instance);
  }
 catch (  Exception e) {
    log.warn("????",e);
    return null;
  }
}
