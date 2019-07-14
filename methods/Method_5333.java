@Override public final long open(DataSpec dataSpec) throws IOException {
  Cipher cipher;
  try {
    cipher=getCipherInstance();
  }
 catch (  NoSuchAlgorithmException|NoSuchPaddingException e) {
    throw new RuntimeException(e);
  }
  Key cipherKey=new SecretKeySpec(encryptionKey,"AES");
  AlgorithmParameterSpec cipherIV=new IvParameterSpec(encryptionIv);
  try {
    cipher.init(Cipher.DECRYPT_MODE,cipherKey,cipherIV);
  }
 catch (  InvalidKeyException|InvalidAlgorithmParameterException e) {
    throw new RuntimeException(e);
  }
  DataSourceInputStream inputStream=new DataSourceInputStream(upstream,dataSpec);
  cipherInputStream=new CipherInputStream(inputStream,cipher);
  inputStream.open();
  return C.LENGTH_UNSET;
}
