private CompletableFuture<List<Multihash>> put(PublicKeyHash writer,List<byte[]> blocks,boolean isRaw){
  return CompletableFuture.completedFuture(blocks.stream().map(b -> {
    Cid cid=hashToCid(b,isRaw);
    put(cid,b);
    return cid;
  }
).collect(Collectors.toList()));
}
