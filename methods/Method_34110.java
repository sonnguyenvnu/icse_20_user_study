public void saveAccessToken(OAuth2ProtectedResourceDetails resource,Authentication authentication,OAuth2AccessToken accessToken){
  removeAccessToken(resource,authentication);
  String name=authentication == null ? null : authentication.getName();
  jdbcTemplate.update(insertAccessTokenSql,new Object[]{accessToken.getValue(),new SqlLobValue(SerializationUtils.serialize(accessToken)),keyGenerator.extractKey(resource,authentication),name,resource.getClientId()},new int[]{Types.VARCHAR,Types.BLOB,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
}
