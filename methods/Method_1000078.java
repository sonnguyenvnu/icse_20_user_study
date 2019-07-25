@Override public boolean validate() throws ContractValidateException {
  if (!VMConfig.getEnergyLimitHardFork()) {
    throw new ContractValidateException("contract type error,unexpected type [UpdateEnergyLimitContract]");
  }
  if (this.contract == null) {
    throw new ContractValidateException("No contract!");
  }
  if (this.dbManager == null) {
    throw new ContractValidateException("No dbManager!");
  }
  if (!this.contract.is(UpdateEnergyLimitContract.class)) {
    throw new ContractValidateException("contract type error,expected type [UpdateEnergyLimitContract],real type[" + contract.getClass() + "]");
  }
  final UpdateEnergyLimitContract contract;
  try {
    contract=this.contract.unpack(UpdateEnergyLimitContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    throw new ContractValidateException(e.getMessage());
  }
  if (!Wallet.addressValid(contract.getOwnerAddress().toByteArray())) {
    throw new ContractValidateException("Invalid address");
  }
  byte[] ownerAddress=contract.getOwnerAddress().toByteArray();
  String readableOwnerAddress=StringUtil.createReadableString(ownerAddress);
  AccountStore accountStore=dbManager.getAccountStore();
  AccountCapsule accountCapsule=accountStore.get(ownerAddress);
  if (accountCapsule == null) {
    throw new ContractValidateException("Account[" + readableOwnerAddress + "] not exists");
  }
  long newOriginEnergyLimit=contract.getOriginEnergyLimit();
  if (newOriginEnergyLimit <= 0) {
    throw new ContractValidateException("origin energy limit must > 0");
  }
  byte[] contractAddress=contract.getContractAddress().toByteArray();
  ContractCapsule deployedContract=dbManager.getContractStore().get(contractAddress);
  if (deployedContract == null) {
    throw new ContractValidateException("Contract not exists");
  }
  byte[] deployedContractOwnerAddress=deployedContract.getInstance().getOriginAddress().toByteArray();
  if (!Arrays.equals(ownerAddress,deployedContractOwnerAddress)) {
    throw new ContractValidateException("Account[" + readableOwnerAddress + "] is not the owner of the contract");
  }
  return true;
}
