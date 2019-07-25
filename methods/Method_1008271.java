/** 
 * Initializes an unassigned shard on a node.
 * @param existingAllocationId allocation id to use. If null, a fresh allocation id is generated.
 */
public ShardRouting initialize(String nodeId,@Nullable String existingAllocationId,long expectedShardSize){
  assert state == ShardRoutingState.UNASSIGNED : this;
  assert relocatingNodeId == null : this;
  final AllocationId allocationId;
  if (existingAllocationId == null) {
    allocationId=AllocationId.newInitializing();
  }
 else {
    allocationId=AllocationId.newInitializing(existingAllocationId);
  }
  return new ShardRouting(shardId,nodeId,null,primary,ShardRoutingState.INITIALIZING,recoverySource,unassignedInfo,allocationId,expectedShardSize);
}
