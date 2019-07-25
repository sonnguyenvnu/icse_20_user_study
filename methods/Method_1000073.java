@Override public boolean execute(TransactionResultCapsule ret) throws ContractExeException {
  long fee=calcFee();
  final SellStorageContract sellStorageContract;
  try {
    sellStorageContract=contract.unpack(SellStorageContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    ret.setStatus(fee,code.FAILED);
    throw new ContractExeException(e.getMessage());
  }
  AccountCapsule accountCapsule=dbManager.getAccountStore().get(sellStorageContract.getOwnerAddress().toByteArray());
  long bytes=sellStorageContract.getStorageBytes();
  storageMarket.sellStorage(accountCapsule,bytes);
  ret.setStatus(fee,code.SUCESS);
  return true;
}
