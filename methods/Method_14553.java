static final public void registerOAuthProvider(Provider provider,String[] oauthInfo){
  providers.put(provider.getHost(),provider);
  infos.put(provider.getHost(),oauthInfo);
}
