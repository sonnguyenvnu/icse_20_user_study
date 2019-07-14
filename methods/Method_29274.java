@Override public List<String> handleSentinelDefaultConfig(String masterName,String host,int port,int sentinelPort){
  List<String> configs=new ArrayList<String>();
  configs.add(RedisSentinelConfigEnum.PORT.getKey() + " " + String.format(RedisSentinelConfigEnum.PORT.getValue(),sentinelPort));
  configs.add(RedisSentinelConfigEnum.DIR.getKey() + " " + RedisSentinelConfigEnum.DIR.getValue());
  configs.add(RedisSentinelConfigEnum.MONITOR.getKey() + " " + String.format(RedisSentinelConfigEnum.MONITOR.getValue(),masterName,host,port,1));
  configs.add(RedisSentinelConfigEnum.DOWN_AFTER_MILLISECONDS.getKey() + " " + String.format(RedisSentinelConfigEnum.DOWN_AFTER_MILLISECONDS.getValue(),masterName));
  configs.add(RedisSentinelConfigEnum.FAILOVER_TIMEOUT.getKey() + " " + String.format(RedisSentinelConfigEnum.FAILOVER_TIMEOUT.getValue(),masterName));
  configs.add(RedisSentinelConfigEnum.PARALLEL_SYNCS.getKey() + " " + String.format(RedisSentinelConfigEnum.PARALLEL_SYNCS.getValue(),masterName));
  return configs;
}
