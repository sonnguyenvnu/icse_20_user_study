@POST public Response create(@Valid SeverityLevel severityLevel,@HeaderParam(HttpKeys.AUTHORIZATION) String userToken){
  try {
    severityLevel.setUserToken(userToken);
    log.debug("Creating Role with information: {}",severityLevel);
    return this.getHandlerFactory().getHandler(Handler.SEVERITY_LEVEL_SERVICE).createRecord(severityLevel);
  }
 catch (  Exception e) {
    log.error("Error while creating severity levels details =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
