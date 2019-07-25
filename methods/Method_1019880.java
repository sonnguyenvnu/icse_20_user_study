@POST public Response create(@Valid SMTPDetail smtpDetail,@HeaderParam(HttpKeys.AUTHORIZATION) String userToken){
  try {
    smtpDetail.setUserToken(userToken);
    log.debug("Creating Role with information: {}",smtpDetail);
    return this.getHandlerFactory().getHandler(Handler.SMTP_SERVICE).createRecord(smtpDetail);
  }
 catch (  Exception e) {
    log.error("Error while creating smtp details =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
