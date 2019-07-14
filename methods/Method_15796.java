@Override public AccessTokenInfo insert(AccessTokenInfo accessTokenInfo){
  accessTokenInfo.setCreateTime(System.currentTimeMillis());
  accessTokenInfo.setUpdateTime(System.currentTimeMillis());
  if (accessTokenInfo.getId() == null) {
    accessTokenInfo.setId(IDGenerator.MD5.generate());
  }
  accessTokenInfoRepo.put(accessTokenInfo.getId(),accessTokenInfo);
  return accessTokenInfo;
}
