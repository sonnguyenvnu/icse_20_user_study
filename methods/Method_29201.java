private Map<String,String> parseMap(final Jedis jedis){
  final StringBuilder builder=new StringBuilder();
  boolean isInfo=new IdempotentConfirmer(){
    @Override public boolean execute(){
      String persistenceInfo=null;
      try {
        persistenceInfo=jedis.info("Persistence");
      }
 catch (      Exception e) {
        logger.warn(e.getMessage() + "-{}:{}",jedis.getClient().getHost(),jedis.getClient().getPort(),e.getMessage());
      }
      boolean isOk=StringUtils.isNotBlank(persistenceInfo);
      if (isOk) {
        builder.append(persistenceInfo);
      }
      return isOk;
    }
  }
.run();
  if (!isInfo) {
    logger.error("{}:{} info Persistence failed",jedis.getClient().getHost(),jedis.getClient().getPort());
    return Collections.emptyMap();
  }
  String persistenceInfo=builder.toString();
  if (StringUtils.isBlank(persistenceInfo)) {
    return Collections.emptyMap();
  }
  Map<String,String> map=new LinkedHashMap<String,String>();
  String[] array=persistenceInfo.split("\r\n");
  for (  String line : array) {
    String[] cells=line.split(":");
    if (cells.length > 1) {
      map.put(cells[0],cells[1]);
    }
  }
  return map;
}
