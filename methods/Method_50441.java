private Date acquireData(){
  return new Date(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - (hmilyConfig.getRecoverDelayTime() * 1000));
}
