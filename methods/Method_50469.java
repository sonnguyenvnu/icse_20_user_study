@SuppressWarnings("unchecked") private HmilyTransaction buildByCache(final MongoAdapter cache){
  try {
    HmilyTransaction hmilyTransaction=new HmilyTransaction();
    hmilyTransaction.setTransId(cache.getTransId());
    hmilyTransaction.setCreateTime(cache.getCreateTime());
    hmilyTransaction.setLastTime(cache.getLastTime());
    hmilyTransaction.setRetriedCount(cache.getRetriedCount());
    hmilyTransaction.setVersion(cache.getVersion());
    hmilyTransaction.setStatus(cache.getStatus());
    hmilyTransaction.setRole(cache.getRole());
    hmilyTransaction.setPattern(cache.getPattern());
    hmilyTransaction.setTargetClass(cache.getTargetClass());
    hmilyTransaction.setTargetMethod(cache.getTargetMethod());
    List<HmilyParticipant> hmilyParticipants=(List<HmilyParticipant>)objectSerializer.deSerialize(cache.getContents(),CopyOnWriteArrayList.class);
    hmilyTransaction.setHmilyParticipants(hmilyParticipants);
    return hmilyTransaction;
  }
 catch (  HmilyException e) {
    LogUtil.error(LOGGER,"mongodb deSerialize exception:{}",e::getLocalizedMessage);
    return null;
  }
}
