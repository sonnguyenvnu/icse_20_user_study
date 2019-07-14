@Override public List<AccessTokenInfo> findByServerIdAndGrantType(String serverId,String grantType){
  return accessTokenInfoRepo.values().stream().filter(token -> token.getServerId().equals(serverId) && token.getGrantType().equals(grantType)).collect(Collectors.toList());
}
