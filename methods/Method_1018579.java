default CompletableFuture<Multihash> put(PublicKeyHash owner,PublicKeyHash writer,byte[] signature,byte[] block,TransactionId tid){
  return put(owner,writer,Arrays.asList(signature),Arrays.asList(block),tid).thenApply(hashes -> hashes.get(0));
}
