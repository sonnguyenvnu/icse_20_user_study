public static void decryptBytesWithKeyFile(byte[] bytes,int offset,int length,SecureDocumentKey secureDocumentKey){
  Utilities.aesCbcEncryptionByteArraySafe(bytes,secureDocumentKey.file_key,secureDocumentKey.file_iv,offset,length,0,0);
}
