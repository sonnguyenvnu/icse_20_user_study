@Override @Caching(evict={@CacheEvict(key="'ownerId:'+#result.ownerId",condition="#result!=null"),@CacheEvict(key="'id:'+#result.id",condition="#result!=null")}) public OAuth2Client remove(String id){
  OAuth2Client old=getClientById(id);
  oAuth2ClientDao.deleteByPk(id);
  return old;
}
