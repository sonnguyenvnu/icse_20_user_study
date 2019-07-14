protected <T>T convertAuthentication(String json){
  if (authenticationBuilderFactory != null) {
    return (T)authenticationBuilderFactory.create().json(json).build();
  }
 else {
    throw new UnsupportedOperationException("authenticationBuilderFactory not ready");
  }
}
