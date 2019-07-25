public byte[] serialize(TransactionXid xid,Object obj){
  CompensableArchive archive=(CompensableArchive)obj;
  CompensableInvocation compensable=archive.getCompensable();
  byte[] byteArray=new byte[0];
  try {
    byteArray=SerializeUtils.serializeObject(compensable);
  }
 catch (  Exception ex) {
    if (compensable == null) {
      logger.error("Error occurred while serializing compensable: {}",compensable,ex);
    }
 else {
      logger.error("Error occurred while serializing args: {}",compensable.getArgs(),ex);
    }
  }
  String transactionResourceKey=archive.getTransactionResourceKey();
  String compensableResourceKey=archive.getCompensableResourceKey();
  byte[] transactionResourceKeyByteArray=transactionResourceKey == null ? new byte[0] : transactionResourceKey.getBytes();
  byte[] compensableResourceKeyByteArray=compensableResourceKey == null ? new byte[0] : compensableResourceKey.getBytes();
  byte[] resultArray=new byte[XidFactory.GLOBAL_TRANSACTION_LENGTH + XidFactory.BRANCH_QUALIFIER_LENGTH + LENGTH_OF_XID * 2 + 1 + 2 + transactionResourceKeyByteArray.length + 2 + compensableResourceKeyByteArray.length + byteArray.length];
  Xid identifier=archive.getIdentifier();
  byte[] globalByteArray=identifier.getGlobalTransactionId();
  byte[] branchByteArray=identifier.getBranchQualifier();
  System.arraycopy(globalByteArray,0,resultArray,0,XidFactory.GLOBAL_TRANSACTION_LENGTH);
  System.arraycopy(branchByteArray,0,resultArray,XidFactory.GLOBAL_TRANSACTION_LENGTH,XidFactory.BRANCH_QUALIFIER_LENGTH);
  Xid transactionXid=archive.getTransactionXid();
  Xid compensableXid=archive.getCompensableXid();
  byte[] transactionGlobalTransactionId=null;
  byte[] transactionBranchQualifier=null;
  byte[] compensableGlobalTransactionId=null;
  byte[] compensableBranchQualifier=null;
  if (transactionXid == null) {
    transactionGlobalTransactionId=new byte[XidFactory.GLOBAL_TRANSACTION_LENGTH];
    transactionBranchQualifier=new byte[XidFactory.BRANCH_QUALIFIER_LENGTH];
  }
 else {
    transactionGlobalTransactionId=transactionXid.getGlobalTransactionId();
    transactionBranchQualifier=transactionXid.getBranchQualifier();
  }
  System.arraycopy(transactionGlobalTransactionId,0,resultArray,LENGTH_OF_XID,XidFactory.GLOBAL_TRANSACTION_LENGTH);
  System.arraycopy(transactionBranchQualifier,0,resultArray,LENGTH_OF_XID + XidFactory.GLOBAL_TRANSACTION_LENGTH,XidFactory.BRANCH_QUALIFIER_LENGTH);
  if (compensableXid == null) {
    compensableGlobalTransactionId=new byte[XidFactory.GLOBAL_TRANSACTION_LENGTH];
    compensableBranchQualifier=new byte[XidFactory.BRANCH_QUALIFIER_LENGTH];
  }
 else {
    compensableGlobalTransactionId=compensableXid.getGlobalTransactionId();
    compensableBranchQualifier=compensableXid.getBranchQualifier();
  }
  System.arraycopy(compensableGlobalTransactionId,0,resultArray,LENGTH_OF_XID * 2,XidFactory.GLOBAL_TRANSACTION_LENGTH);
  System.arraycopy(compensableBranchQualifier,0,resultArray,LENGTH_OF_XID * 2 + XidFactory.GLOBAL_TRANSACTION_LENGTH,XidFactory.BRANCH_QUALIFIER_LENGTH);
  int value=archive.isCoordinator() ? 0x1 : 0x0;
  int triedValue=archive.isTried() ? 0x1 : 0x0;
  int confirmValue=archive.isConfirmed() ? 0x1 : 0x0;
  int cancelValue=archive.isCancelled() ? 0x1 : 0x0;
  value=value | (triedValue << 1);
  value=value | (confirmValue << 2);
  value=value | (cancelValue << 3);
  resultArray[LENGTH_OF_XID * 3]=(byte)value;
  byte[] lengthOfTransactionResourceKey=ByteUtils.shortToByteArray((short)transactionResourceKeyByteArray.length);
  byte[] lengthOfCompensableResourceKey=ByteUtils.shortToByteArray((short)compensableResourceKeyByteArray.length);
  int index=LENGTH_OF_XID * 3 + 1;
  System.arraycopy(lengthOfTransactionResourceKey,0,resultArray,index,lengthOfTransactionResourceKey.length);
  index+=lengthOfTransactionResourceKey.length;
  System.arraycopy(transactionResourceKeyByteArray,0,resultArray,index,transactionResourceKeyByteArray.length);
  index+=transactionResourceKeyByteArray.length;
  System.arraycopy(lengthOfCompensableResourceKey,0,resultArray,index,lengthOfCompensableResourceKey.length);
  index+=lengthOfCompensableResourceKey.length;
  System.arraycopy(compensableResourceKeyByteArray,0,resultArray,index,compensableResourceKeyByteArray.length);
  index+=compensableResourceKeyByteArray.length;
  System.arraycopy(byteArray,0,resultArray,index,byteArray.length);
  return resultArray;
}
