public boolean lock(){
  long nowTime=System.nanoTime();
  long timeout=timeOut * 1000000;
  final Random random=new Random();
  while ((System.nanoTime() - nowTime) < timeout) {
    if (redisTemplate.opsForValue().setIfAbsent(key,LOCKED)) {
      isLocked=true;
      redisTemplate.expire(key,expireTime,TimeUnit.SECONDS);
      break;
    }
    try {
      Thread.sleep(10,random.nextInt(50000));
    }
 catch (    InterruptedException e) {
      logger.error("????????????",e);
    }
  }
  return isLocked;
}
