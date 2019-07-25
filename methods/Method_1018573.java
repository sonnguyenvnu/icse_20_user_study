private static CompletableFuture<NetworkAccess> build(CoreNode core,ContentAddressedStorage localDht,HttpPoster apiPoster,HttpPoster p2pPoster,List<String> usernames,boolean isPeergosServer,boolean isJavascript){
  return localDht.id().exceptionally(t -> new Multihash(Multihash.Type.sha2_256,new byte[32])).thenApply(nodeId -> {
    ContentAddressedStorageProxy proxingDht=new ContentAddressedStorageProxy.HTTP(p2pPoster);
    ContentAddressedStorage p2pDht=isPeergosServer ? localDht : new ContentAddressedStorage.Proxying(localDht,proxingDht,nodeId,core);
    MutablePointersProxy httpMutable=new HttpMutablePointers(apiPoster,p2pPoster);
    MutablePointers p2pMutable=isPeergosServer ? httpMutable : new ProxyingMutablePointers(nodeId,core,httpMutable,httpMutable);
    SocialNetworkProxy httpSocial=new HttpSocialNetwork(apiPoster,p2pPoster);
    SocialNetwork p2pSocial=isPeergosServer ? httpSocial : new ProxyingSocialNetwork(nodeId,core,httpSocial,httpSocial);
    SpaceUsageProxy httpUsage=new HttpSpaceUsage(apiPoster,p2pPoster);
    SpaceUsage p2pUsage=isPeergosServer ? httpUsage : new ProxyingSpaceUsage(nodeId,core,httpUsage,httpUsage);
    return build(p2pDht,core,p2pMutable,p2pSocial,new HttpInstanceAdmin(apiPoster),p2pUsage,usernames,isJavascript);
  }
);
}
