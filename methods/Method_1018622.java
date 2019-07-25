public static CompletableFuture<Boolean> register(String username,SigningPrivateKeyAndPublicHash signer,NetworkAccess network){
  LocalDate now=LocalDate.now();
  LocalDate expiry=now.plusMonths(2);
  LOG.info("claiming username: " + username + " with expiry " + expiry);
  return network.dhtClient.id().thenCompose(id -> {
    List<UserPublicKeyLink> claimChain=UserPublicKeyLink.createInitial(signer,username,expiry,Arrays.asList(id));
    return network.coreNode.getChain(username).thenCompose(existing -> {
      if (existing.size() > 0)       throw new IllegalStateException("User already exists!");
      return network.coreNode.updateChain(username,claimChain);
    }
);
  }
);
}
