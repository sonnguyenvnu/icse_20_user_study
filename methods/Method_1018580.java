@Override public CompletableFuture<List<Multihash>> put(PublicKeyHash owner,PublicKeyHash writer,List<byte[]> signatures,List<byte[]> blocks,TransactionId tid){
  return source.put(owner,writer,signatures,blocks,tid).thenApply(hashes -> hashes.stream().map(h -> verify(blocks.get(hashes.indexOf(h)),h,() -> h)).collect(Collectors.toList()));
}
