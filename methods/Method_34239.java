public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
  ClientDetails details=clientDetailsStore.get(clientId);
  if (details == null) {
    throw new NoSuchClientException("No client with requested id: " + clientId);
  }
  return details;
}
