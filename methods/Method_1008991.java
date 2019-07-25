public void initialize(EncryptionInfo info,CipherAlgorithm cipherAlgorithm,HashAlgorithm hashAlgorithm,int keyBits,int blockSize,ChainingMode chainingMode){
  this.info=info;
  header=new BinaryRC4EncryptionHeader();
  verifier=new BinaryRC4EncryptionVerifier();
  decryptor=new BinaryRC4Decryptor(this);
  encryptor=new BinaryRC4Encryptor(this);
}
