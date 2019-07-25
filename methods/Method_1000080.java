@Override public boolean execute(TransactionResultCapsule ret) throws ContractExeException {
  long fee=calcFee();
  try {
    VoteWitnessContract voteContract=contract.unpack(VoteWitnessContract.class);
    countVoteAccount(voteContract,getDeposit());
    ret.setStatus(fee,code.SUCESS);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    ret.setStatus(fee,code.FAILED);
    throw new ContractExeException(e.getMessage());
  }
  return true;
}
