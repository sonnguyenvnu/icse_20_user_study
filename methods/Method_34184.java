public OAuth2ClientContext getObject() throws Exception {
  if (resource instanceof ClientCredentialsResourceDetails) {
    return bareContext;
  }
  return scopedContext;
}
