@Override public ServeEvent handleRequest(Request request){
  if (requireHttps && !URI.create(request.getAbsoluteUrl()).getScheme().equals("https")) {
    notifier().info("HTTPS is required for admin requests, sending upgrade redirect");
    return ServeEvent.of(LoggedRequest.createFrom(request),ResponseDefinition.notPermitted("HTTPS is required for accessing the admin API"));
  }
  if (!authenticator.authenticate(request)) {
    notifier().info("Authentication failed for " + request.getMethod() + " " + request.getUrl());
    return ServeEvent.of(LoggedRequest.createFrom(request),ResponseDefinition.notAuthorised());
  }
  notifier().info("Admin request received:\n" + formatRequest(request));
  String path=Urls.getPath(withoutAdminRoot(request.getUrl()));
  try {
    AdminTask adminTask=adminRoutes.taskFor(request.getMethod(),path);
    AdminUriTemplate uriTemplate=adminRoutes.requestSpecForTask(adminTask.getClass()).getUriTemplate();
    PathParams pathParams=uriTemplate.parse(path);
    return ServeEvent.of(LoggedRequest.createFrom(request),adminTask.execute(admin,request,pathParams));
  }
 catch (  NotFoundException e) {
    return ServeEvent.forUnmatchedRequest(LoggedRequest.createFrom(request));
  }
catch (  InvalidInputException iie) {
    return ServeEvent.forBadRequest(LoggedRequest.createFrom(request),iie.getErrors());
  }
catch (  NotPermittedException npe) {
    return ServeEvent.forNotAllowedRequest(LoggedRequest.createFrom(request),npe.getErrors());
  }
}
