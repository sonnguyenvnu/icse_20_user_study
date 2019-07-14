public void afterPropertiesSet() throws Exception {
  Assert.notNull(protectedResourceDetailsService,"A protected resource details service is required.");
  Assert.notNull(objectDefinitionSource,"The object definition source must be configured.");
}
