@DELETE @Path("/app/logout") public Response logout(@HeaderParam(HttpKeys.AUTHORIZATION) String accessToken){
  try {
    User user=new User();
    user.setUserToken(accessToken);
    return this.getHandlerFactory().getHandler(Handler.USER_ACCOUNT_SERVICE).updateRecord(user);
  }
 catch (  Exception e) {
    log.error("Error while getting logout =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
