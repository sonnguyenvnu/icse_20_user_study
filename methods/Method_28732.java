protected Object readProtocolWithCheckingBroken(){
  Object o=null;
  try {
    o=Protocol.read(inputStream);
    return o;
  }
 catch (  JedisConnectionException exc) {
    UsefulDataCollector.collectException(exc,getHostPort(),System.currentTimeMillis());
    broken=true;
    throw exc;
  }
 finally {
    UsefulDataModel costModel=UsefulDataModel.getCostModel(threadLocal);
    costModel.setHostPort(getHostPort());
    costModel.setEndTime(System.currentTimeMillis());
    if (o != null) {
      if (o instanceof byte[]) {
        byte[] bytes=(byte[])o;
        costModel.setValueBytesLength(bytes.length);
      }
    }
    threadLocal.remove();
    if (costModel.getCommand() != null) {
      UsefulDataCollector.collectCostAndValueDistribute(costModel);
    }
  }
}
