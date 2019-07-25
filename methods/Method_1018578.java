default CompletableFuture<Multihash> put(PublicKeyHash owner,SigningPrivateKeyAndPublicHash writer,byte[] block,TransactionId tid){
  return put(owner,writer.publicKeyHash,writer.secret.signatureOnly(block),block,tid);
}
