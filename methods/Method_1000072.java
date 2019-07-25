@Override public boolean validate() throws ContractValidateException {
  if (this.contract == null) {
    throw new ContractValidateException("No contract!");
  }
  if (dbManager == null && (getDeposit() == null || getDeposit().getDbManager() == null)) {
    throw new ContractValidateException("No dbManager!");
  }
  if (!this.contract.is(ProposalApproveContract.class)) {
    throw new ContractValidateException("contract type error,expected type [ProposalApproveContract],real type[" + contract.getClass() + "]");
  }
  final ProposalApproveContract contract;
  try {
    contract=this.contract.unpack(ProposalApproveContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    throw new ContractValidateException(e.getMessage());
  }
  byte[] ownerAddress=contract.getOwnerAddress().toByteArray();
  String readableOwnerAddress=StringUtil.createReadableString(ownerAddress);
  if (!Wallet.addressValid(ownerAddress)) {
    throw new ContractValidateException("Invalid address");
  }
  if (!Objects.isNull(getDeposit())) {
    if (Objects.isNull(getDeposit().getAccount(ownerAddress))) {
      throw new ContractValidateException(ACCOUNT_EXCEPTION_STR + readableOwnerAddress + NOT_EXIST_STR);
    }
  }
 else   if (!dbManager.getAccountStore().has(ownerAddress)) {
    throw new ContractValidateException(ACCOUNT_EXCEPTION_STR + readableOwnerAddress + NOT_EXIST_STR);
  }
  if (!Objects.isNull(getDeposit())) {
    if (Objects.isNull(getDeposit().getWitness(ownerAddress))) {
      throw new ContractValidateException(WITNESS_EXCEPTION_STR + readableOwnerAddress + NOT_EXIST_STR);
    }
  }
 else   if (!dbManager.getWitnessStore().has(ownerAddress)) {
    throw new ContractValidateException(WITNESS_EXCEPTION_STR + readableOwnerAddress + NOT_EXIST_STR);
  }
  long latestProposalNum=Objects.isNull(getDeposit()) ? dbManager.getDynamicPropertiesStore().getLatestProposalNum() : getDeposit().getLatestProposalNum();
  if (contract.getProposalId() > latestProposalNum) {
    throw new ContractValidateException(PROPOSAL_EXCEPTION_STR + contract.getProposalId() + NOT_EXIST_STR);
  }
  long now=dbManager.getHeadBlockTimeStamp();
  ProposalCapsule proposalCapsule;
  try {
    proposalCapsule=Objects.isNull(getDeposit()) ? dbManager.getProposalStore().get(ByteArray.fromLong(contract.getProposalId())) : getDeposit().getProposalCapsule(ByteArray.fromLong(contract.getProposalId()));
  }
 catch (  ItemNotFoundException ex) {
    throw new ContractValidateException(PROPOSAL_EXCEPTION_STR + contract.getProposalId() + NOT_EXIST_STR);
  }
  if (now >= proposalCapsule.getExpirationTime()) {
    throw new ContractValidateException(PROPOSAL_EXCEPTION_STR + contract.getProposalId() + "] expired");
  }
  if (proposalCapsule.getState() == State.CANCELED) {
    throw new ContractValidateException(PROPOSAL_EXCEPTION_STR + contract.getProposalId() + "] canceled");
  }
  if (!contract.getIsAddApproval()) {
    if (!proposalCapsule.getApprovals().contains(contract.getOwnerAddress())) {
      throw new ContractValidateException(WITNESS_EXCEPTION_STR + readableOwnerAddress + "]has not approved proposal[" + contract.getProposalId() + "] before");
    }
  }
 else {
    if (proposalCapsule.getApprovals().contains(contract.getOwnerAddress())) {
      throw new ContractValidateException(WITNESS_EXCEPTION_STR + readableOwnerAddress + "]has approved proposal[" + contract.getProposalId() + "] before");
    }
  }
  return true;
}
