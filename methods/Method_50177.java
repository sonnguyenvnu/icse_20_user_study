@Override protected void configureSchema(){
  addQueryList(serviceToFields(ClusterManagerClient.class,ImmutableList.of("listClusters","getCluster","listOperations","getOperation","getServerConfig","listNodePools","getNodePool")));
  addMutationList(serviceToFields(ClusterManagerClient.class,ImmutableList.of("createCluster","updateCluster","setNodePoolAutoscaling","setLoggingService","setMonitoringService","setAddonsConfig","setLocations","updateMaster","setMasterAuth","deleteCluster","cancelOperation","createNodePool","deleteNodePool","rollbackNodePoolUpgrade","setNodePoolManagement","setLabels","setLabels","startIPRotation","completeIPRotation","setNodePoolSize","setNetworkPolicy","setMaintenancePolicy")));
}
