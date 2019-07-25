@JsMethod public CompletableFuture<Boolean> unfollow(String friendName){
  LOG.info("Unfollowing: " + friendName);
  return getUserRoot().thenCompose(home -> home.appendToChild(BLOCKED_USERNAMES_FILE,(friendName + "\n").getBytes(),true,network,crypto,x -> {
  }
)).thenApply(b -> {
    entrie=entrie.removeEntry("/" + friendName + "/");
    return true;
  }
);
}
