public CompletableFuture<Boolean> contains(PublicKeyHash ownedKey){
  return champ.get(keyToBytes(ownedKey)).thenApply(MaybeMultihash::isPresent);
}
