private ResponseHandler targetHandler(final ResponseHandler responseHandler){
  if (this.handler == null) {
    return responseHandler;
  }
  return and(this.handler,responseHandler);
}
