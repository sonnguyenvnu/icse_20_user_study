public CompletableFuture<Snapshot> mkdir(Snapshot base,Committer committer,String name,NetworkAccess network,WritableAbsoluteCapability us,Optional<SigningPrivateKeyAndPublicHash> entryWriter,SymmetricKey optionalBaseKey,boolean isSystemFolder,Crypto crypto){
  SymmetricKey dirReadKey=optionalBaseKey != null ? optionalBaseKey : SymmetricKey.random();
  SymmetricKey dirWriteKey=SymmetricKey.random();
  byte[] dirMapKey=crypto.random.randomBytes(32);
  SymmetricKey ourParentKey=this.getParentKey(us.rBaseKey);
  RelativeCapability ourCap=new RelativeCapability(us.getMapKey(),ourParentKey,null);
  RelativeCapability nextChunk=new RelativeCapability(Optional.empty(),crypto.random.randomBytes(32),dirReadKey,Optional.empty());
  WritableAbsoluteCapability childCap=us.withBaseKey(dirReadKey).withBaseWriteKey(dirWriteKey).withMapKey(dirMapKey);
  DirAndChildren child=CryptreeNode.createDir(MaybeMultihash.empty(),dirReadKey,dirWriteKey,Optional.empty(),new FileProperties(name,true,"",0,LocalDateTime.now(),isSystemFolder,Optional.empty()),Optional.of(ourCap),SymmetricKey.random(),nextChunk,crypto.hasher);
  SymmetricLink toChildWriteKey=SymmetricLink.fromPair(us.wBaseKey.get(),dirWriteKey);
  return IpfsTransaction.call(us.owner,tid -> child.commit(base,committer,childCap,entryWriter,network,tid),network.dhtClient).thenCompose(updatedBase -> {
    RelativeCapability subdirPointer=new RelativeCapability(dirMapKey,dirReadKey,toChildWriteKey);
    return addChildAndCommit(updatedBase,committer,subdirPointer,us,entryWriter,network,crypto);
  }
);
}
