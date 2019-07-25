@Override public boolean validate() throws ContractValidateException {
  if (this.contract == null) {
    throw new ContractValidateException("No contract!");
  }
  if (this.dbManager == null) {
    throw new ContractValidateException("No dbManager!");
  }
  if (!this.contract.is(UnfreezeBalanceContract.class)) {
    throw new ContractValidateException("contract type error,expected type [UnfreezeBalanceContract],real type[" + contract.getClass() + "]");
  }
  final UnfreezeBalanceContract unfreezeBalanceContract;
  try {
    unfreezeBalanceContract=this.contract.unpack(UnfreezeBalanceContract.class);
  }
 catch (  InvalidProtocolBufferException e) {
    logger.debug(e.getMessage(),e);
    throw new ContractValidateException(e.getMessage());
  }
  byte[] ownerAddress=unfreezeBalanceContract.getOwnerAddress().toByteArray();
  if (!Wallet.addressValid(ownerAddress)) {
    throw new ContractValidateException("Invalid address");
  }
  AccountCapsule accountCapsule=dbManager.getAccountStore().get(ownerAddress);
  if (accountCapsule == null) {
    String readableOwnerAddress=StringUtil.createReadableString(ownerAddress);
    throw new ContractValidateException("Account[" + readableOwnerAddress + "] not exists");
  }
  long now=dbManager.getHeadBlockTimeStamp();
  byte[] receiverAddress=unfreezeBalanceContract.getReceiverAddress().toByteArray();
  if (!ArrayUtils.isEmpty(receiverAddress) && dbManager.getDynamicPropertiesStore().supportDR()) {
    if (Arrays.equals(receiverAddress,ownerAddress)) {
      throw new ContractValidateException("receiverAddress must not be the same as ownerAddress");
    }
    if (!Wallet.addressValid(receiverAddress)) {
      throw new ContractValidateException("Invalid receiverAddress");
    }
    AccountCapsule receiverCapsule=dbManager.getAccountStore().get(receiverAddress);
    if (dbManager.getDynamicPropertiesStore().getAllowTvmConstantinople() == 0 && receiverCapsule == null) {
      String readableReceiverAddress=StringUtil.createReadableString(receiverAddress);
      throw new ContractValidateException("Receiver Account[" + readableReceiverAddress + "] not exists");
    }
    byte[] key=DelegatedResourceCapsule.createDbKey(unfreezeBalanceContract.getOwnerAddress().toByteArray(),unfreezeBalanceContract.getReceiverAddress().toByteArray());
    DelegatedResourceCapsule delegatedResourceCapsule=dbManager.getDelegatedResourceStore().get(key);
    if (delegatedResourceCapsule == null) {
      throw new ContractValidateException("delegated Resource not exists");
    }
switch (unfreezeBalanceContract.getResource()) {
case BANDWIDTH:
      if (delegatedResourceCapsule.getFrozenBalanceForBandwidth() <= 0) {
        throw new ContractValidateException("no delegatedFrozenBalance(BANDWIDTH)");
      }
    if (dbManager.getDynamicPropertiesStore().getAllowTvmConstantinople() == 0) {
      if (receiverCapsule.getAcquiredDelegatedFrozenBalanceForBandwidth() < delegatedResourceCapsule.getFrozenBalanceForBandwidth()) {
        throw new ContractValidateException("AcquiredDelegatedFrozenBalanceForBandwidth[" + receiverCapsule.getAcquiredDelegatedFrozenBalanceForBandwidth() + "] < delegatedBandwidth[" + delegatedResourceCapsule.getFrozenBalanceForBandwidth() + "]");
      }
    }
 else {
      if (receiverCapsule != null && receiverCapsule.getType() != AccountType.Contract && receiverCapsule.getAcquiredDelegatedFrozenBalanceForBandwidth() < delegatedResourceCapsule.getFrozenBalanceForBandwidth()) {
        throw new ContractValidateException("AcquiredDelegatedFrozenBalanceForBandwidth[" + receiverCapsule.getAcquiredDelegatedFrozenBalanceForBandwidth() + "] < delegatedBandwidth[" + delegatedResourceCapsule.getFrozenBalanceForBandwidth() + "]");
      }
    }
  if (delegatedResourceCapsule.getExpireTimeForBandwidth() > now) {
    throw new ContractValidateException("It's not time to unfreeze.");
  }
break;
case ENERGY:
if (delegatedResourceCapsule.getFrozenBalanceForEnergy() <= 0) {
throw new ContractValidateException("no delegateFrozenBalance(Energy)");
}
if (dbManager.getDynamicPropertiesStore().getAllowTvmConstantinople() == 0) {
if (receiverCapsule.getAcquiredDelegatedFrozenBalanceForEnergy() < delegatedResourceCapsule.getFrozenBalanceForEnergy()) {
throw new ContractValidateException("AcquiredDelegatedFrozenBalanceForEnergy[" + receiverCapsule.getAcquiredDelegatedFrozenBalanceForEnergy() + "] < delegatedEnergy[" + delegatedResourceCapsule.getFrozenBalanceForEnergy() + "]");
}
}
 else {
if (receiverCapsule != null && receiverCapsule.getType() != AccountType.Contract && receiverCapsule.getAcquiredDelegatedFrozenBalanceForEnergy() < delegatedResourceCapsule.getFrozenBalanceForEnergy()) {
throw new ContractValidateException("AcquiredDelegatedFrozenBalanceForEnergy[" + receiverCapsule.getAcquiredDelegatedFrozenBalanceForEnergy() + "] < delegatedEnergy[" + delegatedResourceCapsule.getFrozenBalanceForEnergy() + "]");
}
}
if (delegatedResourceCapsule.getExpireTimeForEnergy(dbManager) > now) {
throw new ContractValidateException("It's not time to unfreeze.");
}
break;
default :
throw new ContractValidateException("ResourceCode error.valid ResourceCode[BANDWIDTH?Energy]");
}
}
 else {
switch (unfreezeBalanceContract.getResource()) {
case BANDWIDTH:
if (accountCapsule.getFrozenCount() <= 0) {
throw new ContractValidateException("no frozenBalance(BANDWIDTH)");
}
long allowedUnfreezeCount=accountCapsule.getFrozenList().stream().filter(frozen -> frozen.getExpireTime() <= now).count();
if (allowedUnfreezeCount <= 0) {
throw new ContractValidateException("It's not time to unfreeze(BANDWIDTH).");
}
break;
case ENERGY:
Frozen frozenBalanceForEnergy=accountCapsule.getAccountResource().getFrozenBalanceForEnergy();
if (frozenBalanceForEnergy.getFrozenBalance() <= 0) {
throw new ContractValidateException("no frozenBalance(Energy)");
}
if (frozenBalanceForEnergy.getExpireTime() > now) {
throw new ContractValidateException("It's not time to unfreeze(Energy).");
}
break;
default :
throw new ContractValidateException("ResourceCode error.valid ResourceCode[BANDWIDTH?Energy]");
}
}
return true;
}
