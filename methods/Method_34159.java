public AuthorizationServerEndpointsConfigurer pathMapping(String defaultPath,String customPath){
  this.patternMap.put(defaultPath,customPath);
  return this;
}
