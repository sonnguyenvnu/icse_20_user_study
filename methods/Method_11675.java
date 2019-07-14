private void setExtrasInItem(Jedis jedis,Request request,Task task){
  if (request.getExtras() != null) {
    String field=DigestUtils.shaHex(request.getUrl());
    String value=JSON.toJSONString(request);
    jedis.hset(getItemKey(task),field,value);
  }
}
