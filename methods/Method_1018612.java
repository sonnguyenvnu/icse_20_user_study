@Override public CompletableFuture<Snapshot> clear(Snapshot version,Committer committer,Transaction transaction){
  return transaction.clear(version,committer,networkAccess);
}
