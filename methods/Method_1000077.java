@Override public boolean execute(TransactionResultCapsule ret) throws ContractExeException {
  long fee=calcFee();
  try {
    UpdateEnergyLimitContract usContract=contract.unpack(UpdateEnergyLimitContract.class);
    long newOriginEnergyLimit=usContract.getOriginEnergyLimit();
    byte[] contractAddress=usContract.getContractAddress().toByteArray();
    ContractCapsule deployedContract=dbManager.getContractStore().get(contractAddress);
    dbManager.getContractStore().put(contractAddress,new ContractCapsule(deployedContract.getInstance().toBuilder().setOriginEnergyLimit(newOriginEnergyLimit).build()));
    ret.setStatus(fee,code.SUCESS);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    ret.setStatus(fee,code.FAILED);
    throw new ContractExeException(e.getMessage());
  }
  return true;
}
