@Override public boolean execute(TransactionResultCapsule ret) throws ContractExeException {
  long fee=calcFee();
  try {
    ClearABIContract usContract=contract.unpack(ClearABIContract.class);
    byte[] contractAddress=usContract.getContractAddress().toByteArray();
    ContractCapsule deployedContract=dbManager.getContractStore().get(contractAddress);
    deployedContract.clearABI();
    dbManager.getContractStore().put(contractAddress,deployedContract);
    ret.setStatus(fee,code.SUCESS);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    ret.setStatus(fee,code.FAILED);
    throw new ContractExeException(e.getMessage());
  }
  return true;
}
