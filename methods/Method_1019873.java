@POST @Path("/list") @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_OCTET_STREAM}) public Response list(@Valid Finding finding,@HeaderParam(HttpKeys.AUTHORIZATION) String userToken){
  try {
    finding.setUserToken(userToken);
    return this.getHandlerFactory().getHandler(Handler.FINDING_SERVICE).getAllRecords(finding);
  }
 catch (  Exception e) {
    log.error("Error while getting repos =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
