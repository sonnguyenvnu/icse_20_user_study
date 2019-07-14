@Override protected AbstractBeanDefinition parseEndpointAndReturnFilter(Element element,ParserContext parserContext,String tokenServicesRef,String serializerRef){
  String resourceId=element.getAttribute("resource-id");
  String entryPointRef=element.getAttribute("entry-point-ref");
  String authenticationManagerRef=element.getAttribute("authentication-manager-ref");
  String tokenExtractorRef=element.getAttribute("token-extractor-ref");
  String entryAuthDetailsSource=element.getAttribute("auth-details-source-ref");
  String stateless=element.getAttribute("stateless");
  BeanDefinitionBuilder protectedResourceFilterBean=BeanDefinitionBuilder.rootBeanDefinition(OAuth2AuthenticationProcessingFilter.class);
  if (StringUtils.hasText(authenticationManagerRef)) {
    protectedResourceFilterBean.addPropertyReference("authenticationManager",authenticationManagerRef);
  }
 else {
    BeanDefinitionBuilder authenticationManagerBean=BeanDefinitionBuilder.rootBeanDefinition(OAuth2AuthenticationManager.class);
    authenticationManagerBean.addPropertyReference("tokenServices",tokenServicesRef);
    if (StringUtils.hasText(resourceId)) {
      authenticationManagerBean.addPropertyValue("resourceId",resourceId);
    }
    protectedResourceFilterBean.addPropertyValue("authenticationManager",authenticationManagerBean.getBeanDefinition());
  }
  if (StringUtils.hasText(entryPointRef)) {
    protectedResourceFilterBean.addPropertyReference("authenticationEntryPoint",entryPointRef);
  }
  if (StringUtils.hasText(entryAuthDetailsSource)) {
    protectedResourceFilterBean.addPropertyReference("authenticationDetailsSource",entryAuthDetailsSource);
  }
  if (StringUtils.hasText(tokenExtractorRef)) {
    protectedResourceFilterBean.addPropertyReference("tokenExtractor",tokenExtractorRef);
  }
  if (StringUtils.hasText(stateless)) {
    protectedResourceFilterBean.addPropertyValue("stateless",stateless);
  }
  return protectedResourceFilterBean.getBeanDefinition();
}
