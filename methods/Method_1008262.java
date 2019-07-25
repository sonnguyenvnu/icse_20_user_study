/** 
 * Mark a shard as started and adjusts internal statistics.
 * @return the started shard
 */
private ShardRouting started(ShardRouting shard){
  assert shard.initializing() : "expected an initializing shard " + shard;
  if (shard.relocatingNodeId() == null) {
    inactiveShardCount--;
    if (shard.primary()) {
      inactivePrimaryCount--;
    }
  }
  removeRecovery(shard);
  ShardRouting startedShard=shard.moveToStarted();
  updateAssigned(shard,startedShard);
  return startedShard;
}
