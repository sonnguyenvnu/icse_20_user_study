public CompletableFuture<Snapshot> commit(PublicKeyHash owner,SigningPrivateKeyAndPublicHash signer,MaybeMultihash currentHash,MutablePointers mutable,ContentAddressedStorage immutable,TransactionId tid){
  byte[] raw=serialize();
  return immutable.put(owner,signer.publicKeyHash,signer.secret.signatureOnly(raw),raw,tid).thenCompose(blobHash -> {
    MaybeMultihash newHash=MaybeMultihash.of(blobHash);
    if (newHash.equals(currentHash)) {
      CommittedWriterData committed=committed(newHash);
      return CompletableFuture.completedFuture(new Snapshot(signer.publicKeyHash,committed));
    }
    HashCasPair cas=new HashCasPair(currentHash,newHash);
    byte[] signed=signer.secret.signMessage(cas.serialize());
    return mutable.setPointer(owner,signer.publicKeyHash,signed).thenApply(res -> {
      if (!res)       throw new IllegalStateException("Corenode Crypto CAS failed!");
      CommittedWriterData committed=committed(newHash);
      return new Snapshot(signer.publicKeyHash,committed);
    }
);
  }
);
}
