private CompletableFuture<List<Multihash>> put(PublicKeyHash writer,List<byte[]> signatures,List<byte[]> blocks,String format,TransactionId tid){
  CompletableFuture<List<Multihash>> res=new CompletableFuture<>();
  try {
    res.complete(ipfs.block.put(blocks,Optional.of(format)).stream().map(n -> n.hash).collect(Collectors.toList()));
  }
 catch (  Exception e) {
    res.completeExceptionally(e);
  }
  return res;
}
