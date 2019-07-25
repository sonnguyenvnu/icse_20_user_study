@Override public boolean execute(TransactionResultCapsule ret) throws ContractExeException {
  long fee=calcFee();
  final BuyStorageContract buyStorageContract;
  try {
    buyStorageContract=contract.unpack(BuyStorageContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    ret.setStatus(fee,code.FAILED);
    throw new ContractExeException(e.getMessage());
  }
  AccountCapsule accountCapsule=dbManager.getAccountStore().get(buyStorageContract.getOwnerAddress().toByteArray());
  long quant=buyStorageContract.getQuant();
  storageMarket.buyStorage(accountCapsule,quant);
  ret.setStatus(fee,code.SUCESS);
  return true;
}
