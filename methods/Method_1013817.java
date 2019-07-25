@SuppressWarnings("rawtypes") @Override public void update(Object args,Observable observable){
  if (args instanceof NodeAdded) {
    ClusterMeta clusterMeta=(ClusterMeta)((NodeAdded)args).getNode();
    logger.info("[update][add][{}]{}",getClass().getSimpleName(),clusterMeta.getId());
    handleClusterAdd(clusterMeta);
    return;
  }
  if (args instanceof NodeDeleted) {
    ClusterMeta clusterMeta=(ClusterMeta)((NodeDeleted)args).getNode();
    logger.info("[update][delete][{}]{}",getClass().getSimpleName(),clusterMeta.getId());
    handleClusterDeleted(clusterMeta);
    return;
  }
  if (args instanceof ClusterMetaComparator) {
    ClusterMetaComparator clusterMetaComparator=(ClusterMetaComparator)args;
    logger.info("[update][modify][{}]{}",getClass().getSimpleName(),clusterMetaComparator);
    handleClusterModified(clusterMetaComparator);
    return;
  }
  throw new IllegalArgumentException("unknown argument:" + args);
}
