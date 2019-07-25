private CompletableFuture<UserContext> init(Consumer<String> progressCallback){
  progressCallback.accept("Retrieving Friends");
  return writeSynchronizer.getValue(signer.publicKeyHash,signer.publicKeyHash).thenCompose(wd -> createFileTree(entrie,username,network,crypto).thenCompose(root -> {
    this.entrie=root;
    return getByPath("/" + username + "/" + "shared").thenCompose(sharedOpt -> {
      if (!sharedOpt.isPresent())       throw new IllegalStateException("Couldn't find shared folder!");
      return buildSharedWithCache(sharedOpt.get(),this::getUserRoot);
    }
);
  }
)).thenApply(res -> this);
}
