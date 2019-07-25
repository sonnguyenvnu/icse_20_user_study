@PUT @Path("/{id}") public Response update(@PathParam("id") long id,@Valid Finding finding,@HeaderParam(HttpKeys.AUTHORIZATION) String userToken){
  try {
    finding.setId(id);
    finding.setUserToken(userToken);
    log.debug("Updating repo with information: {}",finding);
    return this.getHandlerFactory().getHandler(Handler.FINDING_SERVICE).updateRecord(finding);
  }
 catch (  Exception e) {
    log.error("Error while updating repo =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
