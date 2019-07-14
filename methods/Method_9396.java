private SecureDocumentKey getSecureDocumentKey(byte[] file_secret,byte[] file_hash){
  byte[] decrypted_file_secret=decryptValueSecret(file_secret,file_hash);
  byte[] file_secret_hash=Utilities.computeSHA512(decrypted_file_secret,file_hash);
  byte[] file_key=new byte[32];
  System.arraycopy(file_secret_hash,0,file_key,0,32);
  byte[] file_iv=new byte[16];
  System.arraycopy(file_secret_hash,32,file_iv,0,16);
  return new SecureDocumentKey(file_key,file_iv);
}
