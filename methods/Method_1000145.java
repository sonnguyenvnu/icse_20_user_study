/** 
 * Initialize the local witnesses
 */
@Override public void init(){
  if (Args.getInstance().getLocalWitnesses().getPrivateKeys().size() == 0) {
    return;
  }
  byte[] privateKey=ByteArray.fromHexString(Args.getInstance().getLocalWitnesses().getPrivateKey());
  byte[] witnessAccountAddress=Args.getInstance().getLocalWitnesses().getWitnessAccountAddress();
  byte[] privateKeyAccountAddress=ECKey.fromPrivate(privateKey).getAddress();
  WitnessCapsule witnessCapsule=this.tronApp.getDbManager().getWitnessStore().get(witnessAccountAddress);
  if (null == witnessCapsule) {
    logger.warn("WitnessCapsule[" + witnessAccountAddress + "] is not in witnessStore");
    witnessCapsule=new WitnessCapsule(ByteString.copyFrom(witnessAccountAddress));
  }
  this.privateKeyMap.put(witnessCapsule.getAddress(),privateKey);
  this.localWitnessStateMap.put(witnessCapsule.getAddress(),witnessCapsule);
  this.privateKeyToAddressMap.put(privateKey,privateKeyAccountAddress);
}
