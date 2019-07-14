@Override @Cacheable(key="'ownerId:'+#ownerId") public OAuth2Client getClientByOwnerId(String ownerId){
  return DefaultDSLQueryService.createQuery(oAuth2ClientDao).where("ownerId",ownerId).single();
}
