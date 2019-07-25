public Response handle(Throwable exception){
  ErrorResponseModel model=new ErrorResponseModel();
  model.setErrorCode(CustomErrorCodes.SERVICE_INTERNAL_EXCEPTION);
  model.setMessage(ExceptionMessages.INTERNAL_ERROR);
  log.error("Unknown error...",exception);
  return Response.status(CustomErrorCodes.INTERNAL_ERROR.getValue()).entity(model).build();
}
