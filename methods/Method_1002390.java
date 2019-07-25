@Override public void register(String clusterName,BasePartitionAccessor accessor){
  List<BasePartitionAccessor> accessors=_partitionAccessors.computeIfAbsent(clusterName,k -> Collections.synchronizedList(new ArrayList<>()));
  accessors.add(accessor);
  _log.info("Register partitionAccessor for cluster: {} class: {} (total {})",new Object[]{clusterName,accessor.getClass().getSimpleName(),accessors.size()});
}
