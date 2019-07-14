@Override public List<HmilyTransaction> listAll(){
  List<HmilyTransaction> transactionRecovers=Lists.newArrayList();
  List<String> zNodePaths;
  try {
    zNodePaths=zooKeeper.getChildren(rootPathPrefix,false);
  }
 catch (  Exception e) {
    throw new HmilyRuntimeException(e);
  }
  if (CollectionUtils.isNotEmpty(zNodePaths)) {
    transactionRecovers=zNodePaths.stream().filter(StringUtils::isNoneBlank).map(zNodePath -> {
      try {
        byte[] content=zooKeeper.getData(buildRootPath(zNodePath),false,new Stat());
        return RepositoryConvertUtils.transformBean(content,objectSerializer);
      }
 catch (      KeeperException|InterruptedException|HmilyException e) {
        e.printStackTrace();
      }
      return null;
    }
).collect(Collectors.toList());
  }
  return transactionRecovers;
}
