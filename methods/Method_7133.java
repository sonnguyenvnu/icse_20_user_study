private boolean decryptWithMtProtoVersion(NativeByteBuffer is,byte[] keyToDecrypt,byte[] messageKey,int version,boolean incoming,boolean encryptOnError){
  if (version == 1) {
    incoming=false;
  }
  MessageKeyData keyData=MessageKeyData.generateMessageKeyData(keyToDecrypt,messageKey,incoming,version);
  Utilities.aesIgeEncryption(is.buffer,keyData.aesKey,keyData.aesIv,false,false,24,is.limit() - 24);
  int len=is.readInt32(false);
  byte[] messageKeyFull;
  if (version == 2) {
    messageKeyFull=Utilities.computeSHA256(keyToDecrypt,88 + (incoming ? 8 : 0),32,is.buffer,24,is.buffer.limit());
    if (!Utilities.arraysEquals(messageKey,0,messageKeyFull,8)) {
      if (encryptOnError) {
        Utilities.aesIgeEncryption(is.buffer,keyData.aesKey,keyData.aesIv,true,false,24,is.limit() - 24);
        is.position(24);
      }
      return false;
    }
  }
 else {
    int l=len + 28;
    if (l < is.buffer.limit() - 15 || l > is.buffer.limit()) {
      l=is.buffer.limit();
    }
    messageKeyFull=Utilities.computeSHA1(is.buffer,24,l);
    if (!Utilities.arraysEquals(messageKey,0,messageKeyFull,messageKeyFull.length - 16)) {
      if (encryptOnError) {
        Utilities.aesIgeEncryption(is.buffer,keyData.aesKey,keyData.aesIv,true,false,24,is.limit() - 24);
        is.position(24);
      }
      return false;
    }
  }
  if (len <= 0 || len > is.limit() - 28) {
    return false;
  }
  int padding=is.limit() - 28 - len;
  if (version == 2 && (padding < 12 || padding > 1024) || version == 1 && padding > 15) {
    return false;
  }
  return true;
}
