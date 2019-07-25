@Override public HttpResponse serve(ServiceRequestContext ctx,AggregatedHttpRequest req,String defaultHostname,SamlPortConfig portConfig){
  try {
    final MessageContext<LogoutRequest> messageContext;
    if (endpoint.bindingProtocol() == SamlBindingProtocol.HTTP_REDIRECT) {
      messageContext=HttpRedirectBindingUtil.toSamlObject(req,SAML_REQUEST,idpConfigs,defaultIdpConfig);
    }
 else {
      messageContext=HttpPostBindingUtil.toSamlObject(req,SAML_REQUEST);
    }
    final String endpointUri=endpoint.toUriString(portConfig.scheme().uriText(),defaultHostname,portConfig.port());
    final LogoutRequest logoutRequest=messageContext.getMessage();
    final SamlIdentityProviderConfig idp=validateAndGetIdPConfig(logoutRequest,endpointUri);
    if (endpoint.bindingProtocol() == SamlBindingProtocol.HTTP_POST) {
      validateSignature(idp.signingCredential(),logoutRequest);
    }
    final SamlEndpoint sloResEndpoint=idp.sloResEndpoint().orElse(null);
    if (sloResEndpoint == null) {
      return HttpResponse.from(sloHandler.logoutSucceeded(ctx,req,messageContext).thenApply(unused -> HttpResponse.of(HttpStatus.OK)));
    }
    final LogoutResponse logoutResponse=createLogoutResponse(logoutRequest,StatusCode.SUCCESS);
    try {
      final HttpResponse response=respond(logoutResponse,sloResEndpoint);
      return HttpResponse.from(sloHandler.logoutSucceeded(ctx,req,messageContext).thenApply(unused -> response));
    }
 catch (    SamlException e) {
      logger.warn("{} Cannot respond a logout response in response to {}",ctx,logoutRequest.getID(),e);
      final HttpResponse response=fail(ctx,logoutRequest,sloResEndpoint);
      return HttpResponse.from(sloHandler.logoutFailed(ctx,req,e).thenApply(unused -> response));
    }
  }
 catch (  SamlException e) {
    return fail(ctx,e);
  }
}
