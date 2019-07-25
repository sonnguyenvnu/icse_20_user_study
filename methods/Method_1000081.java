@Override public boolean validate() throws ContractValidateException {
  if (this.contract == null) {
    throw new ContractValidateException("No contract!");
  }
  if (dbManager == null && (getDeposit() == null || getDeposit().getDbManager() == null)) {
    throw new ContractValidateException("No dbManager!");
  }
  if (!this.contract.is(VoteWitnessContract.class)) {
    throw new ContractValidateException("contract type error,expected type [VoteWitnessContract],real type[" + contract.getClass() + "]");
  }
  final VoteWitnessContract contract;
  try {
    contract=this.contract.unpack(VoteWitnessContract.class);
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
  WitnessStore witnessStore=dbManager.getWitnessStore();
  if (contract.getVotesCount() == 0) {
    throw new ContractValidateException("VoteNumber must more than 0");
  }
  int maxVoteNumber=ChainConstant.MAX_VOTE_NUMBER;
  if (contract.getVotesCount() > maxVoteNumber) {
    throw new ContractValidateException("VoteNumber more than maxVoteNumber " + maxVoteNumber);
  }
  try {
    Iterator<Vote> iterator=contract.getVotesList().iterator();
    Long sum=0L;
    while (iterator.hasNext()) {
      Vote vote=iterator.next();
      byte[] witnessCandidate=vote.getVoteAddress().toByteArray();
      if (!Wallet.addressValid(witnessCandidate)) {
        throw new ContractValidateException("Invalid vote address!");
      }
      long voteCount=vote.getVoteCount();
      if (voteCount <= 0) {
        throw new ContractValidateException("vote count must be greater than 0");
      }
      String readableWitnessAddress=StringUtil.createReadableString(vote.getVoteAddress());
      if (!Objects.isNull(getDeposit())) {
        if (Objects.isNull(getDeposit().getAccount(witnessCandidate))) {
          throw new ContractValidateException(ACCOUNT_EXCEPTION_STR + readableWitnessAddress + NOT_EXIST_STR);
        }
      }
 else       if (!accountStore.has(witnessCandidate)) {
        throw new ContractValidateException(ACCOUNT_EXCEPTION_STR + readableWitnessAddress + NOT_EXIST_STR);
      }
      if (!Objects.isNull(getDeposit())) {
        if (Objects.isNull(getDeposit().getWitness(witnessCandidate))) {
          throw new ContractValidateException(WITNESS_EXCEPTION_STR + readableWitnessAddress + NOT_EXIST_STR);
        }
      }
 else       if (!witnessStore.has(witnessCandidate)) {
        throw new ContractValidateException(WITNESS_EXCEPTION_STR + readableWitnessAddress + NOT_EXIST_STR);
      }
      sum=LongMath.checkedAdd(sum,vote.getVoteCount());
    }
    AccountCapsule accountCapsule=(Objects.isNull(getDeposit())) ? accountStore.get(ownerAddress) : getDeposit().getAccount(ownerAddress);
    if (accountCapsule == null) {
      throw new ContractValidateException(ACCOUNT_EXCEPTION_STR + readableOwnerAddress + NOT_EXIST_STR);
    }
    long tronPower=accountCapsule.getTronPower();
    sum=LongMath.checkedMultiply(sum,1000000L);
    if (sum > tronPower) {
      throw new ContractValidateException("The total number of votes[" + sum + "] is greater than the tronPower[" + tronPower + "]");
    }
  }
 catch (  ArithmeticException e) {
    logger.debug(e.getMessage(),e);
    throw new ContractValidateException(e.getMessage());
  }
  return true;
}
