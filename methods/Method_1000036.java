public void suicide(DataWord obtainerAddress){
  byte[] owner=convertToTronAddress(getContractAddress().getLast20Bytes());
  byte[] obtainer=convertToTronAddress(obtainerAddress.getLast20Bytes());
  long balance=getContractState().getBalance(owner);
  if (logger.isDebugEnabled()) {
    logger.debug("Transfer to: [{}] heritage: [{}]",Hex.toHexString(obtainer),balance);
  }
  increaseNonce();
  addInternalTx(null,owner,obtainer,balance,null,"suicide",nonce,getContractState().getAccount(owner).getAssetMapV2());
  if (FastByteComparisons.compareTo(owner,0,20,obtainer,0,20) == 0) {
    getContractState().addBalance(owner,-balance);
    byte[] blackHoleAddress=getContractState().getBlackHoleAddress();
    if (VMConfig.allowTvmTransferTrc10()) {
      getContractState().addBalance(blackHoleAddress,balance);
      transferAllToken(getContractState(),owner,blackHoleAddress);
    }
  }
 else {
    try {
      transfer(getContractState(),owner,obtainer,balance);
      if (VMConfig.allowTvmTransferTrc10()) {
        transferAllToken(getContractState(),owner,obtainer);
      }
    }
 catch (    ContractValidateException e) {
      if (VMConfig.allowTvmConstantinople()) {
        throw new TransferException("transfer all token or transfer all trx failed in suicide: %s",e.getMessage());
      }
      throw new BytecodeExecutionException("transfer failure");
    }
  }
  getResult().addDeleteAccount(this.getContractAddress());
}
