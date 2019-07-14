public void afterPropertiesSet() throws Exception {
  Assert.notNull(protectedResourceDetailsService,"A protected resource details service is required.");
  Assert.notNull(streamHandlerFactory,"A stream handler factory is required.");
}
