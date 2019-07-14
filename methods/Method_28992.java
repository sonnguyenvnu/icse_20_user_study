public JedisPool build(){
  if (jedisPool == null) {
    while (true) {
      try {
        LOCK.tryLock(100,TimeUnit.MILLISECONDS);
        if (jedisPool == null) {
          String response=HttpUtils.doGet(String.format(ConstUtils.REDIS_STANDALONE_URL,appId));
          if (response == null || response.isEmpty()) {
            logger.warn("cannot get response from server, appId={}. continue...",appId);
            continue;
          }
          JSONObject jsonObject=null;
          try {
            jsonObject=JSONObject.parseObject(response);
          }
 catch (          Exception e) {
            logger.error("read json from response error, appId: {}.",appId,e);
          }
          if (jsonObject == null) {
            logger.warn("invalid response, appId: {}. continue...",appId);
            continue;
          }
          String instance=jsonObject.getString("standalone");
          String[] instanceArr=instance.split(":");
          if (instanceArr.length != 2) {
            logger.warn("instance info is invalid, instance: {}, appId: {}, continue...",instance,appId);
            continue;
          }
          if (clientStatIsOpen) {
            ClientDataCollectReportExecutor.getInstance();
          }
          String password=jsonObject.getString("password");
          if (StringUtil.isBlank(password)) {
            jedisPool=new JedisPool(poolConfig,instanceArr[0],Integer.valueOf(instanceArr[1]),timeout);
          }
 else {
            jedisPool=new JedisPool(poolConfig,instanceArr[0],Integer.valueOf(instanceArr[1]),timeout,password);
          }
          return jedisPool;
        }
      }
 catch (      InterruptedException e) {
        logger.error("error in build().",e);
      }
    }
  }
  return jedisPool;
}
