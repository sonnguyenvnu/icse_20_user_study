public <T>T decrypt(SymmetricKey from,Function<CborObject,T> fromCbor){
  return cipherText.decrypt(from,fromCbor);
}
