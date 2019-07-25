@RequestMapping(method=RequestMethod.GET) public List<KeeperInstanceMeta> list(){
  logger.info("[list]");
  List<KeeperInstanceMeta> keepers=FluentIterable.from(keeperContainerService.list()).transform(new Function<RedisKeeperServer,KeeperInstanceMeta>(){
    @Override public KeeperInstanceMeta apply(    RedisKeeperServer server){
      return server.getKeeperInstanceMeta();
    }
  }
).toList();
  return keepers;
}
