@Override public KeeperMeta select(String clusterId,String shardId,List<KeeperMeta> toBeSelected){
  if (toBeSelected.size() == 0) {
    return null;
  }
  for (  KeeperMeta keeperMeta : userDefinedPriority) {
    for (    KeeperMeta select : toBeSelected) {
      if (MetaUtils.same(keeperMeta,select)) {
        return select;
      }
    }
  }
  logger.warn("[select][no keeper in given list, use first]{}, {}",userDefinedPriority,toBeSelected);
  return toBeSelected.get(0);
}
