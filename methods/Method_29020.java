public String getUniqKey(){
  return redisValueSizeEnum.getValue() + "_" + hostPort + "_" + command;
}
