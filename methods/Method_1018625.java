public void put(PublicKeyHash owner,PublicKeyHash writer,CommittedWriterData val){
  pending.put(new Pair<>(owner,writer),new AsyncLock<>(CompletableFuture.completedFuture(new Snapshot(writer,val))));
}
