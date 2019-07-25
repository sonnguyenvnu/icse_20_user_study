@Override public boolean validate() throws ContractValidateException {
  if (this.contract == null) {
    throw new ContractValidateException("No contract!");
  }
  if (this.dbManager == null) {
    throw new ContractValidateException("No dbManager!");
  }
  if (this.dbManager.getDynamicPropertiesStore().getAllowMultiSign() != 1) {
    throw new ContractValidateException("multi sign is not allowed, " + "need to be opened by the committee");
  }
  if (!this.contract.is(AccountPermissionUpdateContract.class)) {
    throw new ContractValidateException("contract type error,expected type [AccountPermissionUpdateContract],real type[" + contract.getClass() + "]");
  }
  final AccountPermissionUpdateContract accountPermissionUpdateContract;
  try {
    accountPermissionUpdateContract=contract.unpack(AccountPermissionUpdateContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    throw new ContractValidateException(e.getMessage());
  }
  byte[] ownerAddress=accountPermissionUpdateContract.getOwnerAddress().toByteArray();
  if (!Wallet.addressValid(ownerAddress)) {
    throw new ContractValidateException("invalidate ownerAddress");
  }
  AccountCapsule accountCapsule=dbManager.getAccountStore().get(ownerAddress);
  if (accountCapsule == null) {
    throw new ContractValidateException("ownerAddress account does not exist");
  }
  if (!accountPermissionUpdateContract.hasOwner()) {
    throw new ContractValidateException("owner permission is missed");
  }
  if (accountCapsule.getIsWitness()) {
    if (!accountPermissionUpdateContract.hasWitness()) {
      throw new ContractValidateException("witness permission is missed");
    }
  }
 else {
    if (accountPermissionUpdateContract.hasWitness()) {
      throw new ContractValidateException("account isn't witness can't set witness permission");
    }
  }
  if (accountPermissionUpdateContract.getActivesCount() == 0) {
    throw new ContractValidateException("active permission is missed");
  }
  if (accountPermissionUpdateContract.getActivesCount() > 8) {
    throw new ContractValidateException("active permission is too many");
  }
  Permission owner=accountPermissionUpdateContract.getOwner();
  Permission witness=accountPermissionUpdateContract.getWitness();
  List<Permission> actives=accountPermissionUpdateContract.getActivesList();
  if (owner.getType() != PermissionType.Owner) {
    throw new ContractValidateException("owner permission type is error");
  }
  if (!checkPermission(owner)) {
    return false;
  }
  if (accountCapsule.getIsWitness()) {
    if (witness.getType() != PermissionType.Witness) {
      throw new ContractValidateException("witness permission type is error");
    }
    if (!checkPermission(witness)) {
      return false;
    }
  }
  for (  Permission permission : actives) {
    if (permission.getType() != PermissionType.Active) {
      throw new ContractValidateException("active permission type is error");
    }
    if (!checkPermission(permission)) {
      return false;
    }
  }
  return true;
}
