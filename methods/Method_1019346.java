@Override protected String invoke(PayTransactionSuccessMessage message,DubboReferencePool.ReferenceMeta referenceMeta){
  PayTransactionDO transaction=payTransactionMapper.selectById(message.getTransactionId());
  Assert.notNull(transaction,String.format("????(%s) ????????",message.toString()));
  GenericService genericService=referenceMeta.getService();
  String methodName=referenceMeta.getMethodName();
  return (String)genericService.$invoke(methodName,new String[]{String.class.getName(),Integer.class.getName()},new Object[]{message.getOrderId(),transaction.getPrice()});
}
