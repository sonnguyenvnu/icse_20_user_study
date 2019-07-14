@RequestMapping(value="/add/{appId}/{type}/{host}/{port}") public void addJob(@PathVariable long appId,@PathVariable int type,@PathVariable String host,@PathVariable int port){
  Assert.isTrue(appId > 0);
  Assert.isTrue(type > 0);
  Assert.hasText(host);
  Assert.isTrue(port > 0);
  redisCenter.deployRedisCollection(appId,host,port);
  logger.info("deploy instance: {}:{} done.",host,port);
}
