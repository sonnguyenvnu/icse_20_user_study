private static byte[] decrypt(byte[] key,byte[] cipher,byte[] nonce,Salsa20Poly1305 implementation){
  return implementation.secretbox_open(cipher,nonce,key);
}
