/** 
 * Relocate the shard to another node.
 * @param relocatingNodeId id of the node to relocate the shard
 */
public ShardRouting relocate(String relocatingNodeId,long expectedShardSize){
  assert state == ShardRoutingState.STARTED : "current shard has to be started in order to be relocated " + this;
  return new ShardRouting(shardId,currentNodeId,relocatingNodeId,primary,ShardRoutingState.RELOCATING,recoverySource,null,AllocationId.newRelocation(allocationId),expectedShardSize);
}
