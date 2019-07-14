private long getExecuteTime(RpNotifyRecord record){
  long lastTime=record.getLastNotifyTime().getTime();
  Integer nextNotifyTime=notifyParam.getNotifyParams().get(record.getNotifyTimes());
  return (nextNotifyTime == null ? 0 : nextNotifyTime * 1000) + lastTime;
}
