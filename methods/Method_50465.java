@SuppressWarnings("unchecked") private HmilyTransaction buildByResultMap(final Map<String,Object> map){
  HmilyTransaction hmilyTransaction=new HmilyTransaction();
  hmilyTransaction.setTransId((String)map.get("trans_id"));
  hmilyTransaction.setRetriedCount((Integer)map.get("retried_count"));
  hmilyTransaction.setCreateTime((Date)map.get("create_time"));
  hmilyTransaction.setLastTime((Date)map.get("last_time"));
  hmilyTransaction.setVersion((Integer)map.get("version"));
  hmilyTransaction.setStatus((Integer)map.get("status"));
  hmilyTransaction.setRole((Integer)map.get("role"));
  hmilyTransaction.setPattern((Integer)map.get("pattern"));
  byte[] bytes=(byte[])map.get("invocation");
  try {
    final List<HmilyParticipant> hmilyParticipants=serializer.deSerialize(bytes,CopyOnWriteArrayList.class);
    hmilyTransaction.setHmilyParticipants(hmilyParticipants);
  }
 catch (  HmilyException e) {
    e.printStackTrace();
  }
  return hmilyTransaction;
}
