@Override public List<String> handleCommonDefaultConfig(int port,int maxMemory){
  List<String> configs=new ArrayList<String>();
  for (  RedisConfigEnum config : RedisConfigEnum.values()) {
    if (RedisConfigEnum.MAXMEMORY.equals(config)) {
      configs.add(config.getKey() + " " + String.format(config.getValue(),maxMemory));
    }
 else     if (RedisConfigEnum.DBFILENAME.equals(config) || RedisConfigEnum.APPENDFILENAME.equals(config) || RedisConfigEnum.PORT.equals(config)) {
      configs.add(config.getKey() + " " + String.format(config.getValue(),port));
    }
 else     if (RedisConfigEnum.DIR.equals(config)) {
      configs.add(config.getKey() + " " + MachineProtocol.DATA_DIR);
    }
 else     if (RedisConfigEnum.AUTO_AOF_REWRITE_PERCENTAGE.equals(config)) {
      int percent=69 + new Random().nextInt(30);
      configs.add(config.getKey() + " " + String.format(RedisConfigEnum.AUTO_AOF_REWRITE_PERCENTAGE.getValue(),percent));
    }
 else {
      configs.add(config.getKey() + " " + config.getValue());
    }
  }
  return configs;
}
