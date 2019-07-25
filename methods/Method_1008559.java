public void resync(final IndexShard indexShard,final ActionListener<ResyncTask> listener){
  ActionListener<ResyncTask> resyncListener=null;
  try {
    final long startingSeqNo=indexShard.getGlobalCheckpoint() + 1;
    Translog.Snapshot snapshot=indexShard.getTranslog().newSnapshotFromMinSeqNo(startingSeqNo);
    resyncListener=new ActionListener<ResyncTask>(){
      @Override public void onResponse(      final ResyncTask resyncTask){
        try {
          snapshot.close();
          listener.onResponse(resyncTask);
        }
 catch (        final Exception e) {
          onFailure(e);
        }
      }
      @Override public void onFailure(      final Exception e){
        try {
          snapshot.close();
        }
 catch (        final Exception inner) {
          e.addSuppressed(inner);
        }
 finally {
          listener.onFailure(e);
        }
      }
    }
;
    ShardId shardId=indexShard.shardId();
    Translog.Snapshot wrappedSnapshot=new Translog.Snapshot(){
      @Override public synchronized void close() throws IOException {
        snapshot.close();
      }
      @Override public synchronized int totalOperations(){
        return snapshot.totalOperations();
      }
      @Override public synchronized Translog.Operation next() throws IOException {
        IndexShardState state=indexShard.state();
        if (state == IndexShardState.CLOSED) {
          throw new IndexShardClosedException(shardId);
        }
 else {
          assert state == IndexShardState.STARTED : "resync should only happen on a started shard, but state was: " + state;
        }
        return snapshot.next();
      }
    }
;
    resync(shardId,indexShard.routingEntry().allocationId().getId(),indexShard.getPrimaryTerm(),wrappedSnapshot,startingSeqNo,resyncListener);
  }
 catch (  Exception e) {
    if (resyncListener != null) {
      resyncListener.onFailure(e);
    }
 else {
      listener.onFailure(e);
    }
  }
}
