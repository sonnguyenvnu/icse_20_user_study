/** 
 * Checks if all shards in the list have completed
 * @param shards list of shard statuses
 * @return true if all shards have completed (either successfully or failed), false otherwise
 */
public static boolean completed(ObjectContainer<ShardSnapshotStatus> shards){
  for (  ObjectCursor<ShardSnapshotStatus> status : shards) {
    if (status.value.state().completed() == false) {
      return false;
    }
  }
  return true;
}
