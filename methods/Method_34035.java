public ConsumerDetails loadConsumerByConsumerKey(String consumerKey) throws OAuthException {
  ConsumerDetails details=consumerDetailsStore.get(consumerKey);
  if (details == null) {
    throw new InvalidOAuthParametersException("Consumer not found: " + consumerKey);
  }
  return details;
}
