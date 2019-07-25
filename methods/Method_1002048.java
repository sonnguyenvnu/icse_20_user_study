public AutomationClient provide(ContainerRequest request){
  Optional<Principal> principal=ClientAuthFactory.getPrincipal(request);
  Optional<String> possibleClientName=ClientAuthFactory.getClientName(principal);
  if (!principal.isPresent() || !possibleClientName.isPresent()) {
    throw new NotAuthorizedException("Not authorized as a AutomationClient");
  }
  String clientName=possibleClientName.get();
  return authenticator.authenticate(clientName,principal.orElse(null)).orElseThrow(() -> new ForbiddenException(format("ClientCert name %s not authorized as a AutomationClient",clientName)));
}
