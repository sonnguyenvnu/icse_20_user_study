public void setServers(List<OAuth2ServerConfig> servers){
  this.servers=servers;
  repo=servers.stream().collect(Collectors.toMap(OAuth2ServerConfig::getId,Function.identity()));
}
