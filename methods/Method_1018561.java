private CompletableFuture<Multihash> commit(SigningPrivateKeyAndPublicHash writer,Pair<Champ,Multihash> newRoot){
  root=newRoot;
  return CompletableFuture.completedFuture(newRoot.right);
}
