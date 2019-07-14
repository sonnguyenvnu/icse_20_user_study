public void validateNonce(ConsumerDetails consumerDetails,long timestamp,String nonce){
  if (System.currentTimeMillis() / 1000 - timestamp > getValidityWindowSeconds()) {
    throw new CredentialsExpiredException("Expired timestamp.");
  }
  NonceEntry entry=new NonceEntry(consumerDetails.getConsumerKey(),timestamp,nonce);
synchronized (NONCES) {
    if (NONCES.contains(entry)) {
      throw new NonceAlreadyUsedException("Nonce already used: " + nonce);
    }
 else {
      NONCES.add(entry);
    }
    cleanupNonces();
  }
}
