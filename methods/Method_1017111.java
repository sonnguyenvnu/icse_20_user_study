private ClusterNodeStatus convert(ClusterNode node){
  final NodeMetadata m=node.metadata();
  return new ClusterNodeStatus(node.toString(),m.getId(),m.getVersion(),m.getTags());
}
