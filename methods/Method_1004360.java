private ClusterFuture request(MetaInfoService.MetaInfoRequestParam requestParam,boolean refresh){
  String key=MapKeyBuilder.buildMetaInfoKey(requestParam.getClientType(),requestParam.getSubject());
  ClusterFuture newFuture=new ClusterFuture();
  ClusterFuture oldFuture=clusterMap.putIfAbsent(key,newFuture);
  if (oldFuture != null) {
    if (refresh && !oldFuture.inRequest.get()) {
      oldFuture.inRequest.set(true);
      metaInfoService.requestWrapper(requestParam);
    }
 else {
      metaInfoService.tryAddRequest(requestParam);
    }
    return oldFuture;
  }
  metaInfoService.requestWrapper(requestParam);
  return newFuture;
}
