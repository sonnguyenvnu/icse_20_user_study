@Override public boolean validate() throws ContractValidateException {
  if (!VMConfig.allowTvmConstantinople()) {
    throw new ContractValidateException("contract type error,unexpected type [ClearABIContract]");
  }
  if (this.contract == null) {
    throw new ContractValidateException("No contract!");
  }
  if (this.dbManager == null) {
    throw new ContractValidateException("No dbManager!");
  }
  if (!this.contract.is(ClearABIContract.class)) {
    throw new ContractValidateException("contract type error,expected type [ClearABIContract],real type[" + contract.getClass() + "]");
  }
  final ClearABIContract contract;
  try {
    contract=this.contract.unpack(ClearABIContract.class);
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
