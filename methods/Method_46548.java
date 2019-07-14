@Override public void trace(String groupId,String unitId,TransactionInfo transactionInfo){
  executorService.submit(() -> {
    long t1=System.currentTimeMillis();
    byte[] bytes;
    try {
      bytes=SerializerContext.getInstance().serialize(transactionInfo);
    }
 catch (    SerializerException e) {
      e.printStackTrace();
      return;
    }
    AspectLog txLog=new AspectLog();
    txLog.setBytes(bytes);
    txLog.setGroupId(groupId);
    txLog.setUnitId(unitId);
    txLog.setMethodStr(transactionInfo.getMethodStr());
    txLog.setTime(System.currentTimeMillis());
    txLog.setUnitIdHash(groupId.hashCode());
    txLog.setUnitIdHash(unitId.hashCode());
    boolean res=txLogHelper.save(txLog);
    long t2=System.currentTimeMillis();
    log.debug("async save aspect log. result: {} groupId: {}, used time: {}ms",res,groupId,(t2 - t1));
  }
);
}
