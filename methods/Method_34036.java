public void validateNonce(ConsumerDetails consumerDetails,long timestamp,String nonce) throws AuthenticationException {
  long nowSeconds=(System.currentTimeMillis() / 1000);
  if ((nowSeconds - timestamp) > getValidityWindowSeconds()) {
    throw new CredentialsExpiredException("Expired timestamp.");
  }
}
