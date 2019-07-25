@Override public KeeperMeta select(String clusterId,String shardId,List<KeeperMeta> toBeSelected){
  if (toBeSelected.size() > 0) {
    KeeperMeta result=toBeSelected.get(0);
    result.setActive(true);
    return result;
  }
  return null;
}
