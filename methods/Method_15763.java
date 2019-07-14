@Override public OAuth2Client getClientByOwnerId(String ownerId){
  return clients.values().stream().filter(client -> ownerId.equals(client.getOwnerId())).findFirst().orElse(null);
}
