public CompletableFuture<Snapshot> clear(Snapshot version,Committer committer,NetworkAccess network){
  return Futures.reduceAll(locations,version,(s,loc) -> clear(s,committer,network,loc),(a,b) -> b);
}
