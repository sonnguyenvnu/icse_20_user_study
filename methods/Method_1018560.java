public static CompletableFuture<ChampWrapper> create(Multihash rootHash,Function<ByteArrayWrapper,byte[]> hasher,ContentAddressedStorage dht){
  return dht.get(rootHash).thenApply(rawOpt -> {
    if (!rawOpt.isPresent())     throw new IllegalStateException("Champ root not present: " + rootHash);
    return new ChampWrapper(Champ.fromCbor(rawOpt.get()),rootHash,hasher,dht,BIT_WIDTH);
  }
);
}
