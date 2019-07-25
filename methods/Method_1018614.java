@Override public CompletableFuture<MaybeMultihash> get(WriterData base,PublicKeyHash owner,PublicKeyHash writer,byte[] mapKey){
  if (!base.tree.isPresent())   throw new IllegalStateException("Tree root not present for " + writer);
  return ChampWrapper.create(base.tree.get(),hasher,dht).thenCompose(tree -> tree.get(mapKey)).thenApply(maybe -> LOGGING ? log(maybe,"TREE.get (" + ArrayOps.bytesToHex(mapKey) + ", root=" + base.tree.get() + " => " + maybe) : maybe);
}
