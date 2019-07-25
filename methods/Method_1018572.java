public static CompletableFuture<NetworkAccess> build(HttpPoster apiPoster,HttpPoster p2pPoster,Multihash pkiServerNodeId,boolean isJavascript){
  ContentAddressedStorage localDht=buildLocalDht(apiPoster,true);
  CoreNode direct=buildDirectCorenode(apiPoster);
  CompletableFuture<NetworkAccess> result=new CompletableFuture<>();
  direct.getUsernames("").thenAccept(usernames -> {
    CoreNode core=direct;
    build(core,localDht,apiPoster,p2pPoster,usernames,true,isJavascript).thenApply(result::complete).exceptionally(t -> {
      result.completeExceptionally(t);
      return true;
    }
);
  }
).exceptionally(t -> {
    ContentAddressedStorage localIpfs=buildLocalDht(apiPoster,false);
    CoreNode core=buildProxyingCorenode(p2pPoster,pkiServerNodeId);
    core.getUsernames("").thenCompose(usernames -> build(core,localIpfs,apiPoster,p2pPoster,usernames,false,isJavascript).thenApply(result::complete)).exceptionally(t2 -> {
      result.completeExceptionally(t2);
      return true;
    }
);
    return null;
  }
);
  return result;
}
