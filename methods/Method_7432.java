public void upgradeToGroupCall(List<Integer> usersToAdd){
  if (upgrading)   return;
  groupUsersToAdd=usersToAdd;
  if (!isOutgoing) {
    controller.requestCallUpgrade();
    return;
  }
  upgrading=true;
  groupCallEncryptionKey=new byte[256];
  Utilities.random.nextBytes(groupCallEncryptionKey);
  groupCallEncryptionKey[0]&=0x7F;
  byte[] authKeyHash=Utilities.computeSHA1(groupCallEncryptionKey);
  byte[] authKeyId=new byte[8];
  System.arraycopy(authKeyHash,authKeyHash.length - 8,authKeyId,0,8);
  groupCallKeyFingerprint=Utilities.bytesToLong(authKeyId);
  controller.sendGroupCallKey(groupCallEncryptionKey);
}
