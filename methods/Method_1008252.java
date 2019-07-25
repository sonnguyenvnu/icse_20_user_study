/** 
 * Helper method that creates update entry for the given shard id if such an entry does not exist yet.
 */
private Updates changes(ShardId shardId){
  return shardChanges.computeIfAbsent(shardId,k -> new Updates());
}
