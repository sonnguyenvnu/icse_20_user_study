@Override public OAuth2Client newClient(){
  SimpleOAuth2ClientEntity clientEntity=SimpleOAuth2ClientEntity.builder().build();
  clientEntity.setId(IDGenerator.MD5.generate());
  clientEntity.setSecret(IDGenerator.MD5.generate());
  clientEntity.setStatus(DataStatus.STATUS_ENABLED);
  clientEntity.setCreateTimeNow();
  return clientEntity;
}
