@Override protected void pushWhenNoDuplicate(Request request,Task task){
  Jedis jedis=pool.getResource();
  try {
    if (request.getPriority() > 0)     jedis.zadd(getZsetPlusPriorityKey(task),request.getPriority(),request.getUrl());
 else     if (request.getPriority() < 0)     jedis.zadd(getZsetMinusPriorityKey(task),request.getPriority(),request.getUrl());
 else     jedis.lpush(getQueueNoPriorityKey(task),request.getUrl());
    setExtrasInItem(jedis,request,task);
  }
  finally {
    pool.returnResource(jedis);
  }
}
