@Override public CompletableFuture<List<Multihash>> put(PublicKeyHash owner,PublicKeyHash writer,List<byte[]> signatures,List<byte[]> blocks,TransactionId tid){
  if (!keyFilter.apply(writer,blocks.stream().mapToInt(x -> x.length).sum()))   throw new IllegalStateException("Key not allowed to write to this server: " + writer);
  return dht.put(owner,writer,signatures,blocks,tid);
}
