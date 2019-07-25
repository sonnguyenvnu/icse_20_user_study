@POST @Path("/login") public Response login(@Valid User user){
  try {
    log.debug("Logging user with information: {}",user);
    return this.getHandlerFactory().getHandler(Handler.USER_SERVICE).createRecord(user);
  }
 catch (  Exception e) {
    log.error("Error while logging.... =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
