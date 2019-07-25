boolean validate(MetaData metaData){
  if (!metaData.hasIndex(index.getName())) {
    throw new IllegalStateException(index + " exists in routing does not exists in metadata");
  }
  IndexMetaData indexMetaData=metaData.index(index.getName());
  if (indexMetaData.getIndexUUID().equals(index.getUUID()) == false) {
    throw new IllegalStateException(index.getName() + " exists in routing does not exists in metadata with the same uuid");
  }
  if (indexMetaData.getNumberOfShards() != shards().size()) {
    Set<Integer> expected=new HashSet<>();
    for (int i=0; i < indexMetaData.getNumberOfShards(); i++) {
      expected.add(i);
    }
    for (    IndexShardRoutingTable indexShardRoutingTable : this) {
      expected.remove(indexShardRoutingTable.shardId().id());
    }
    throw new IllegalStateException("Wrong number of shards in routing table, missing: " + expected);
  }
  for (  IndexShardRoutingTable indexShardRoutingTable : this) {
    int routingNumberOfReplicas=indexShardRoutingTable.size() - 1;
    if (routingNumberOfReplicas != indexMetaData.getNumberOfReplicas()) {
      throw new IllegalStateException("Shard [" + indexShardRoutingTable.shardId().id() + "] routing table has wrong number of replicas, expected [" + indexMetaData.getNumberOfReplicas() + "], got [" + routingNumberOfReplicas + "]");
    }
    for (    ShardRouting shardRouting : indexShardRoutingTable) {
      if (!shardRouting.index().equals(index)) {
        throw new IllegalStateException("shard routing has an index [" + shardRouting.index() + "] that is different " + "from the routing table");
      }
      final Set<String> inSyncAllocationIds=indexMetaData.inSyncAllocationIds(shardRouting.id());
      if (shardRouting.active() && inSyncAllocationIds.contains(shardRouting.allocationId().getId()) == false) {
        throw new IllegalStateException("active shard routing " + shardRouting + " has no corresponding entry in the in-sync " + "allocation set " + inSyncAllocationIds);
      }
      if (shardRouting.primary() && shardRouting.initializing() && shardRouting.recoverySource().getType() == RecoverySource.Type.EXISTING_STORE && inSyncAllocationIds.contains(shardRouting.allocationId().getId()) == false)       throw new IllegalStateException("a primary shard routing " + shardRouting + " is a primary that is recovering from " + "a known allocation id but has no corresponding entry in the in-sync " + "allocation set " + inSyncAllocationIds);
    }
  }
  return true;
}
