@Override public boolean execute(TransactionResultCapsule ret) throws ContractExeException {
  long fee=calcFee();
  final BuyStorageBytesContract BuyStorageBytesContract;
  try {
    BuyStorageBytesContract=contract.unpack(BuyStorageBytesContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    ret.setStatus(fee,code.FAILED);
    throw new ContractExeException(e.getMessage());
  }
  AccountCapsule accountCapsule=dbManager.getAccountStore().get(BuyStorageBytesContract.getOwnerAddress().toByteArray());
  long bytes=BuyStorageBytesContract.getBytes();
  storageMarket.buyStorageBytes(accountCapsule,bytes);
  ret.setStatus(fee,code.SUCESS);
  return true;
}
