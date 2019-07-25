public void initialize(EncryptionInfo info,LittleEndianInput dis) throws IOException {
  this.info=info;
  int vMajor=info.getVersionMajor();
  int vMinor=info.getVersionMinor();
  assert (vMajor == 1 && vMinor == 1);
  header=new BinaryRC4EncryptionHeader();
  verifier=new BinaryRC4EncryptionVerifier(dis);
  decryptor=new BinaryRC4Decryptor(this);
  encryptor=new BinaryRC4Encryptor(this);
}
