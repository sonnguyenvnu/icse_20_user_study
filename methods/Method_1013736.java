@Override public void update(String dcId,ClusterMeta clusterMeta){
  DcMeta dcMeta=xpipeMeta.getDcs().get(dcId);
  dcMeta.addCluster(clone(clusterMeta));
}
