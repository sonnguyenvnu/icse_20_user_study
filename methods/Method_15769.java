protected OAuth2Client checkClient(OAuth2Client client){
  if (client == null) {
    throw new GrantTokenException(CLIENT_NOT_EXIST);
  }
  if (!DataStatus.STATUS_ENABLED.equals(client.getStatus())) {
    throw new GrantTokenException(CLIENT_DISABLED);
  }
  return client;
}
