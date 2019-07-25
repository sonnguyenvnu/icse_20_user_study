public CompletableFuture<Multihash> add(PublicKeyHash owner,SigningPrivateKeyAndPublicHash writer,OwnerProof proof,TransactionId tid){
  return ipfs.put(owner,writer,proof.serialize(),tid).thenCompose(valueHash -> champ.put(owner,writer,keyToBytes(proof.ownedKey),MaybeMultihash.empty(),valueHash,tid));
}
