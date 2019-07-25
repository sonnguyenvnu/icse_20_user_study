@Override public void close(){
  if (DiscoveryNode.isDataNode(settings)) {
    clusterService.removeListener(this);
  }
}
