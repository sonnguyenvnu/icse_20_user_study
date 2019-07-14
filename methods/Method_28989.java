public JedisCluster build(){
  if (jedisCluster == null) {
    while (true) {
      try {
        lock.tryLock(10,TimeUnit.SECONDS);
        if (jedisCluster != null) {
          return jedisCluster;
        }
        String url=String.format(ConstUtils.REDIS_CLUSTER_URL,String.valueOf(appId));
        String response=HttpUtils.doGet(url);
        JSONObject jsonObject=null;
        try {
          jsonObject=JSONObject.parseObject(response);
        }
 catch (        Exception e) {
          logger.error("remote build error, appId: {}",appId,e);
        }
        if (jsonObject == null) {
          logger.error("get cluster info for appId: {} error. continue...",appId);
          continue;
        }
        int status=jsonObject.getIntValue("status");
        String message=jsonObject.getString("message");
        if (status == ClientStatusEnum.ERROR.getStatus()) {
          throw new IllegalStateException(message);
        }
 else         if (status == ClientStatusEnum.WARN.getStatus()) {
          logger.warn(message);
        }
 else {
          logger.info(message);
        }
        Set<HostAndPort> nodeList=new HashSet<HostAndPort>();
        String nodeInfo=jsonObject.getString("shardInfo");
        nodeInfo=nodeInfo.replace(" ",",");
        String[] nodeArray=nodeInfo.split(",");
        for (        String node : nodeArray) {
          String[] ipAndPort=node.split(":");
          if (ipAndPort.length < 2) {
            continue;
          }
          String ip=ipAndPort[0];
          int port=Integer.parseInt(ipAndPort[1]);
          nodeList.add(new HostAndPort(ip,port));
        }
        if (clientStatIsOpen) {
          ClientDataCollectReportExecutor.getInstance();
        }
        String password=jsonObject.getString("password");
        if (StringUtil.isBlank(password)) {
          jedisCluster=new JedisCluster(nodeList,connectionTimeout,soTimeout,maxRedirections,jedisPoolConfig);
        }
 else {
          jedisCluster=new JedisCluster(nodeList,connectionTimeout,soTimeout,maxRedirections,password,jedisPoolConfig);
        }
        return jedisCluster;
      }
 catch (      Throwable e) {
        logger.error(e.getMessage(),e);
      }
 finally {
        lock.unlock();
      }
      try {
        TimeUnit.MILLISECONDS.sleep(200 + new Random().nextInt(1000));
      }
 catch (      InterruptedException e) {
        logger.error(e.getMessage(),e);
      }
    }
  }
 else {
    return jedisCluster;
  }
}
