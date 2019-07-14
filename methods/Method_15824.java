protected OAuth2Request createRequest(String uriOrUrl){
  return requestBuilderFactory.create(serverConfig.getId(),serverConfig.getProvider()).url(getRealUrl(uriOrUrl)).build();
}
