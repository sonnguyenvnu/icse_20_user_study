public static Map<HostPort,XpipeMetaManager.MetaDesc> build(XpipeMeta xpipeMeta){
  logger.info("build reverse map");
  Map<HostPort,XpipeMetaManager.MetaDesc> result=new ConcurrentHashMap();
  xpipeMeta.getDcs().forEach((dc,dcMeta) -> {
    dcMeta.getClusters().forEach((clusterId,clusterMeta) -> {
      clusterMeta.getShards().forEach((shardId,shardMeta) -> {
        for (        RedisMeta redisMeta : shardMeta.getRedises()) {
          result.put(new HostPort(redisMeta.getIp(),redisMeta.getPort()),new XpipeMetaManager.MetaDesc(dcMeta,clusterMeta,shardMeta,redisMeta));
        }
        for (        KeeperMeta keeperMeta : shardMeta.getKeepers()) {
          result.put(new HostPort(keeperMeta.getIp(),keeperMeta.getPort()),new XpipeMetaManager.MetaDesc(dcMeta,clusterMeta,shardMeta,keeperMeta));
        }
      }
);
    }
);
  }
);
  return result;
}
