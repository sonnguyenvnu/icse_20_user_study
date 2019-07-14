private byte[] generateTransactionId(){
  String transactionId="T" + Long.toHexString(System.currentTimeMillis());
  return transactionId.getBytes();
}
