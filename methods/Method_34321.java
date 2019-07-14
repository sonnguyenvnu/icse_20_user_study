public void store(OAuth2Request originalRequest,TokenRequest tokenRequest){
  this.requestStore.put(tokenRequest,originalRequest);
}
