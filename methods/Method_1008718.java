private boolean failed(SnapshotInfo snapshot,String index){
  for (  SnapshotShardFailure failure : snapshot.shardFailures()) {
    if (index.equals(failure.index())) {
      return true;
    }
  }
  return false;
}
