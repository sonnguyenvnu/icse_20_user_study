@Activate public void activate(Map<String,Object> properties) throws GeneralSecurityException {
  String cipherTarget=(String)properties.getOrDefault("CIPHER_TARGET",SymmetricKeyCipher.CIPHER_ID);
  storageCipher=allAvailableStorageCiphers.stream().filter(cipher -> cipher.getUniqueCipherId().equals(cipherTarget)).findFirst();
  logger.debug("Using Cipher: {}",storageCipher.orElseThrow(() -> new GeneralSecurityException("No StorageCipher with target=" + cipherTarget)));
}
