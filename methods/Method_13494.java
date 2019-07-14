@Override public RocketMQLocalTransactionState executeLocalTransaction(Message msg,Object arg){
  Object num=msg.getHeaders().get("test");
  if ("1".equals(num)) {
    System.out.println("executer: " + new String((byte[])msg.getPayload()) + " unknown");
    return RocketMQLocalTransactionState.UNKNOWN;
  }
 else   if ("2".equals(num)) {
    System.out.println("executer: " + new String((byte[])msg.getPayload()) + " rollback");
    return RocketMQLocalTransactionState.ROLLBACK;
  }
  System.out.println("executer: " + new String((byte[])msg.getPayload()) + " commit");
  return RocketMQLocalTransactionState.COMMIT;
}
