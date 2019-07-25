@Override public CompletableFuture<Snapshot> open(Snapshot version,Committer committer,Transaction transaction){
  byte[] data=transaction.serialize();
  AsyncReader asyncReader=AsyncReader.build(data);
  return transactionDirUpdater.updated(version).thenCompose(dir -> dir.uploadFileSection(version,committer,transaction.name(),asyncReader,false,0,data.length,Optional.empty(),false,networkAccess,crypto,VOID_PROGRESS,dir.generateChildLocations(1,crypto.random)));
}
