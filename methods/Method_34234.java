public void setRegisteredRedirectUri(Set<String> registeredRedirectUris){
  this.registeredRedirectUris=registeredRedirectUris == null ? null : new LinkedHashSet<String>(registeredRedirectUris);
}
