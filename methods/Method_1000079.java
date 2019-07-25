@Override public boolean execute(TransactionResultCapsule ret) throws ContractExeException {
  long fee=calcFee();
  try {
    UpdateSettingContract usContract=contract.unpack(UpdateSettingContract.class);
    long newPercent=usContract.getConsumeUserResourcePercent();
    byte[] contractAddress=usContract.getContractAddress().toByteArray();
    ContractCapsule deployedContract=dbManager.getContractStore().get(contractAddress);
    dbManager.getContractStore().put(contractAddress,new ContractCapsule(deployedContract.getInstance().toBuilder().setConsumeUserResourcePercent(newPercent).build()));
    ret.setStatus(fee,code.SUCESS);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    ret.setStatus(fee,code.FAILED);
    throw new ContractExeException(e.getMessage());
  }
  return true;
}
