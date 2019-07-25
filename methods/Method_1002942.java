@Override public HttpResponse serve(ServiceRequestContext ctx,AggregatedHttpRequest req,String defaultHostname,SamlPortConfig portConfig){
  try {
    final MessageContext<Response> messageContext;
    if (cfg.endpoint().bindingProtocol() == SamlBindingProtocol.HTTP_REDIRECT) {
      messageContext=HttpRedirectBindingUtil.toSamlObject(req,SAML_RESPONSE,idpConfigs,defaultIdpConfig);
    }
 else {
      messageContext=HttpPostBindingUtil.toSamlObject(req,SAML_RESPONSE);
    }
    final String endpointUri=cfg.endpoint().toUriString(portConfig.scheme().uriText(),defaultHostname,portConfig.port());
    final Response response=messageContext.getMessage();
    final Assertion assertion=getValidatedAssertion(response,endpointUri);
    final String sessionIndex=assertion.getAuthnStatements().stream().map(AuthnStatement::getSessionIndex).filter(Objects::nonNull).findFirst().orElse(null);
    final SAMLBindingContext bindingContext=messageContext.getSubcontext(SAMLBindingContext.class);
    final String relayState=bindingContext != null ? bindingContext.getRelayState() : null;
    return ssoHandler.loginSucceeded(ctx,req,messageContext,sessionIndex,relayState);
  }
 catch (  SamlException e) {
    return ssoHandler.loginFailed(ctx,req,null,e);
  }
}
