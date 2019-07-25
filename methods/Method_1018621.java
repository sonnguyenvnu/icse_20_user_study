@JsMethod public CompletableFuture<Boolean> register(){
  return isRegistered().thenCompose(exists -> {
    if (exists)     throw new IllegalStateException("Account already exists with username: " + username);
    return register(this.username,signer,network);
  }
);
}
