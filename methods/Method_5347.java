private void setEncryptionData(Uri keyUri,String iv,byte[] secretKey){
  String trimmedIv;
  if (Util.toLowerInvariant(iv).startsWith("0x")) {
    trimmedIv=iv.substring(2);
  }
 else {
    trimmedIv=iv;
  }
  byte[] ivData=new BigInteger(trimmedIv,16).toByteArray();
  byte[] ivDataWithPadding=new byte[16];
  int offset=ivData.length > 16 ? ivData.length - 16 : 0;
  System.arraycopy(ivData,offset,ivDataWithPadding,ivDataWithPadding.length - ivData.length + offset,ivData.length - offset);
  encryptionKeyUri=keyUri;
  encryptionKey=secretKey;
  encryptionIvString=iv;
  encryptionIv=ivDataWithPadding;
}
