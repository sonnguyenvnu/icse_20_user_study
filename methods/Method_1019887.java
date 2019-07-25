public Response handle(AbstractException exception){
  ErrorResponseModel model=new ErrorResponseModel();
  model.setErrorCode(exception.getCode());
  model.setMessage(exception.getCode().name());
  log.error("Unknown error...",exception);
  return Response.status(CustomErrorCodes.INTERNAL_ERROR.getValue()).entity(model).build();
}
