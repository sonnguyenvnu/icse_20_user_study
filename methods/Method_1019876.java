@POST public Response create(@Valid Permission permission){
  try {
    log.debug("Creating permission with information: {}",permission);
    return this.getHandlerFactory().getHandler(Handler.PERMISSION_SERVICE).createRecord(permission);
  }
 catch (  Exception e) {
    log.error("Error while creating permission =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
