public void init(AbstractRedisClient redisClient,StatefulConnection connection){
  map.computeIfAbsent(redisClient,key -> {
    LettuceObjects lo=new LettuceObjects();
    lo.connection=connection;
    return lo;
  }
);
}
