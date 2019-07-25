public static boolean completed(ImmutableOpenMap<ShardId,RestoreInProgress.ShardRestoreStatus> shards){
  for (  ObjectCursor<RestoreInProgress.ShardRestoreStatus> status : shards.values()) {
    if (!status.value.state().completed()) {
      return false;
    }
  }
  return true;
}
