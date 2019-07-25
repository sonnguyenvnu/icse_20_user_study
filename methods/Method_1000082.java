@Override public boolean validate() throws ContractValidateException {
  if (this.contract == null) {
    throw new ContractValidateException("No contract!");
  }
  if (this.dbManager == null) {
    throw new ContractValidateException("No dbManager!");
  }
  if (!this.contract.is(WitnessUpdateContract.class)) {
    throw new ContractValidateException("contract type error,expected type [WitnessUpdateContract],real type[" + contract.getClass() + "]");
  }
  final WitnessUpdateContract contract;
  try {
    contract=this.contract.unpack(WitnessUpdateContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    throw new ContractValidateException(e.getMessage());
  }
  byte[] ownerAddress=contract.getOwnerAddress().toByteArray();
  if (!Wallet.addressValid(ownerAddress)) {
    throw new ContractValidateException("Invalid address");
  }
  if (!this.dbManager.getAccountStore().has(ownerAddress)) {
    throw new ContractValidateException("account does not exist");
  }
  if (!TransactionUtil.validUrl(contract.getUpdateUrl().toByteArray())) {
    throw new ContractValidateException("Invalid url");
  }
  if (!this.dbManager.getWitnessStore().has(ownerAddress)) {
    throw new ContractValidateException("Witness does not exist");
  }
  return true;
}
