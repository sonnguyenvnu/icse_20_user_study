@Override public boolean validate() throws ContractValidateException {
  if (this.contract == null) {
    throw new ContractValidateException("No contract!");
  }
  if (this.dbManager == null) {
    throw new ContractValidateException("No dbManager!");
  }
  if (!contract.is(BuyStorageBytesContract.class)) {
    throw new ContractValidateException("contract type error,expected type [BuyStorageBytesContract],real type[" + contract.getClass() + "]");
  }
  final BuyStorageBytesContract BuyStorageBytesContract;
  try {
    BuyStorageBytesContract=this.contract.unpack(BuyStorageBytesContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    throw new ContractValidateException(e.getMessage());
  }
  byte[] ownerAddress=BuyStorageBytesContract.getOwnerAddress().toByteArray();
  if (!Wallet.addressValid(ownerAddress)) {
    throw new ContractValidateException("Invalid address");
  }
  AccountCapsule accountCapsule=dbManager.getAccountStore().get(ownerAddress);
  if (accountCapsule == null) {
    String readableOwnerAddress=StringUtil.createReadableString(ownerAddress);
    throw new ContractValidateException("Account[" + readableOwnerAddress + "] not exists");
  }
  long bytes=BuyStorageBytesContract.getBytes();
  if (bytes < 0) {
    throw new ContractValidateException("bytes must be positive");
  }
  if (bytes < 1L) {
    throw new ContractValidateException("bytes must be larger than 1, current storage_bytes[" + bytes + "]");
  }
  long quant=storageMarket.tryBuyStorageBytes(bytes);
  if (quant < 1_000_000L) {
    throw new ContractValidateException("quantity must be larger than 1TRX");
  }
  if (quant > accountCapsule.getBalance()) {
    throw new ContractValidateException("quantity must be less than accountBalance");
  }
  return true;
}
