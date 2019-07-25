@POST public Response create(@Valid Git git){
  try {
    log.debug("Creating Git with information: {}",git);
    return this.getHandlerFactory().getHandler(Handler.GIT_SERVICE).createRecord(git);
  }
 catch (  Exception e) {
    log.error("Error while creating git =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
