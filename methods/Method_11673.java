private String getRequest(Jedis jedis,Task task){
  String url;
  Set<String> urls=jedis.zrevrange(getZsetPlusPriorityKey(task),0,0);
  if (urls.isEmpty()) {
    url=jedis.lpop(getQueueNoPriorityKey(task));
    if (StringUtils.isBlank(url)) {
      urls=jedis.zrevrange(getZsetMinusPriorityKey(task),0,0);
      if (!urls.isEmpty()) {
        url=urls.toArray(new String[0])[0];
        jedis.zrem(getZsetMinusPriorityKey(task),url);
      }
    }
  }
 else {
    url=urls.toArray(new String[0])[0];
    jedis.zrem(getZsetPlusPriorityKey(task),url);
  }
  return url;
}
