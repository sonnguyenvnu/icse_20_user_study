/** 
 * This needs to be a <code>@Bean</code> so that it can be <code>@Transactional</code> (in case the token store supports them). If you are overriding the token services in an {@link AuthorizationServerConfigurer} consider making it a<code>@Bean</code> for the same reason (assuming you need transactions, e.g. for a JDBC token store).
 * @return an AuthorizationServerTokenServices
 */
@Bean public FactoryBean<AuthorizationServerTokenServices> defaultAuthorizationServerTokenServices(){
  return new AuthorizationServerTokenServicesFactoryBean(endpoints);
}
