public Object commands(AbstractRedisClient redisClient){
  connection(redisClient);
  LettuceObjects lo=getLettuceObjectsFromMap(redisClient);
  if (lo.commands == null) {
    if (lo.connection instanceof StatefulRedisConnection) {
      lo.commands=((StatefulRedisConnection)lo.connection).sync();
    }
 else     if (lo.connection instanceof StatefulRedisClusterConnection) {
      lo.commands=((StatefulRedisClusterConnection)lo.connection).sync();
    }
 else     if (lo.connection instanceof StatefulRedisSentinelConnection) {
      lo.commands=((StatefulRedisSentinelConnection)lo.connection).sync();
    }
 else {
      throw new CacheConfigException("type " + lo.connection.getClass() + " is not supported");
    }
  }
  return lo.commands;
}
