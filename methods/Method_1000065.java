@Override public boolean execute(TransactionResultCapsule result) throws ContractExeException {
  long fee=calcFee();
  final AccountPermissionUpdateContract accountPermissionUpdateContract;
  try {
    accountPermissionUpdateContract=contract.unpack(AccountPermissionUpdateContract.class);
    byte[] ownerAddress=accountPermissionUpdateContract.getOwnerAddress().toByteArray();
    AccountStore accountStore=dbManager.getAccountStore();
    AccountCapsule account=accountStore.get(ownerAddress);
    account.updatePermissions(accountPermissionUpdateContract.getOwner(),accountPermissionUpdateContract.getWitness(),accountPermissionUpdateContract.getActivesList());
    accountStore.put(ownerAddress,account);
    dbManager.adjustBalance(ownerAddress,-fee);
    dbManager.adjustBalance(dbManager.getAccountStore().getBlackhole().createDbKey(),fee);
    result.setStatus(fee,code.SUCESS);
  }
 catch (  BalanceInsufficientException e) {
    logger.debug(e.getMessage(),e);
    result.setStatus(fee,code.FAILED);
    throw new ContractExeException(e.getMessage());
  }
catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    result.setStatus(fee,code.FAILED);
    throw new ContractExeException(e.getMessage());
  }
  return true;
}
