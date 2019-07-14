@Override public long open(DataSpec dataSpec) throws IOException {
  long dataLength=upstream.open(dataSpec);
  long nonce=CryptoUtil.getFNV64Hash(dataSpec.key);
  cipher=new AesFlushingCipher(Cipher.DECRYPT_MODE,secretKey,nonce,dataSpec.absoluteStreamPosition);
  return dataLength;
}
