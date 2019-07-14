@Override public List<String> handleClusterDefaultConfig(int port){
  List<String> configs=new ArrayList<String>();
  for (  RedisClusterConfigEnum config : RedisClusterConfigEnum.values()) {
    if (config.equals(RedisClusterConfigEnum.CLUSTER_CONFIG_FILE)) {
      configs.add(RedisClusterConfigEnum.CLUSTER_CONFIG_FILE.getKey() + " " + String.format(RedisClusterConfigEnum.CLUSTER_CONFIG_FILE.getValue(),port));
    }
 else {
      configs.add(config.getKey() + " " + config.getValue());
    }
  }
  return configs;
}
