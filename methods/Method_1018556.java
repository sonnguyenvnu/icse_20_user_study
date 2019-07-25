public SigningPrivateKeyAndPublicHash target(SymmetricKey from){
  return cipherText.decrypt(from,SigningPrivateKeyAndPublicHash::fromCbor);
}
