public CompletableFuture<CryptreeNode> commit(WritableAbsoluteCapability us,Optional<SigningPrivateKeyAndPublicHash> entryWriter,NetworkAccess network,TransactionId tid){
  return network.uploadChunk(this,us.owner,us.getMapKey(),getSigner(us.rBaseKey,us.wBaseKey.get(),entryWriter),tid).thenApply(this::withHash);
}
