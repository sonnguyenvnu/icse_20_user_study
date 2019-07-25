public SymmetricKey target(SymmetricKey from){
  return cipherText.decrypt(from,SymmetricKey::fromCbor);
}
