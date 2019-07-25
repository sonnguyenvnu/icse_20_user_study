@Override public boolean validate() throws ContractValidateException {
  if (this.contract == null) {
    throw new ContractValidateException("No contract!");
  }
  if (this.dbManager == null) {
    throw new ContractValidateException("No dbManager!");
  }
  if (!this.contract.is(UnfreezeAssetContract.class)) {
    throw new ContractValidateException("contract type error,expected type [UnfreezeAssetContract],real type[" + contract.getClass() + "]");
  }
  final UnfreezeAssetContract unfreezeAssetContract;
  try {
    unfreezeAssetContract=this.contract.unpack(UnfreezeAssetContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    throw new ContractValidateException(e.getMessage());
  }
  byte[] ownerAddress=unfreezeAssetContract.getOwnerAddress().toByteArray();
  if (!Wallet.addressValid(ownerAddress)) {
    throw new ContractValidateException("Invalid address");
  }
  AccountCapsule accountCapsule=dbManager.getAccountStore().get(ownerAddress);
  if (accountCapsule == null) {
    String readableOwnerAddress=StringUtil.createReadableString(ownerAddress);
    throw new ContractValidateException("Account[" + readableOwnerAddress + "] not exists");
  }
  if (accountCapsule.getFrozenSupplyCount() <= 0) {
    throw new ContractValidateException("no frozen supply balance");
  }
  if (dbManager.getDynamicPropertiesStore().getAllowSameTokenName() == 0) {
    if (accountCapsule.getAssetIssuedName().isEmpty()) {
      throw new ContractValidateException("this account did not issue any asset");
    }
  }
 else {
    if (accountCapsule.getAssetIssuedID().isEmpty()) {
      throw new ContractValidateException("this account did not issue any asset");
    }
  }
  long now=dbManager.getHeadBlockTimeStamp();
  long allowedUnfreezeCount=accountCapsule.getFrozenSupplyList().stream().filter(frozen -> frozen.getExpireTime() <= now).count();
  if (allowedUnfreezeCount <= 0) {
    throw new ContractValidateException("It's not time to unfreeze asset supply");
  }
  return true;
}
