@Override public void onGroupCallKeyReceived(byte[] key){
  joiningGroupCall=true;
  groupCallEncryptionKey=key;
  byte[] authKeyHash=Utilities.computeSHA1(groupCallEncryptionKey);
  byte[] authKeyId=new byte[8];
  System.arraycopy(authKeyHash,authKeyHash.length - 8,authKeyId,0,8);
  groupCallKeyFingerprint=Utilities.bytesToLong(authKeyId);
}
