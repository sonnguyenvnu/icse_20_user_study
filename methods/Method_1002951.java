private HttpResponse respond(LogoutResponse logoutResponse,SamlEndpoint sloResEndpoint){
  if (sloResEndpoint.bindingProtocol() == SamlBindingProtocol.HTTP_REDIRECT) {
    return responseWithLocation(toRedirectionUrl(logoutResponse,sloResEndpoint.toUriString(),SAML_RESPONSE,signingCredential,signatureAlgorithm,null));
  }
 else {
    final String value=toSignedBase64(logoutResponse,signingCredential,signatureAlgorithm);
    final HttpData body=getSsoForm(sloResEndpoint.toUriString(),SAML_RESPONSE,value,null);
    return HttpResponse.of(HttpStatus.OK,MediaType.HTML_UTF_8,body);
  }
}
