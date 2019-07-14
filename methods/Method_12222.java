@ExceptionHandler(value=Exception.class) public ResultGeekQ<String> exceptionHandler(HttpServletRequest request,Exception e){
  e.printStackTrace();
  if (e instanceof GlobleException) {
    GlobleException ex=(GlobleException)e;
    return ResultGeekQ.error(ex.getStatus());
  }
 else   if (e instanceof BindException) {
    BindException ex=(BindException)e;
    List<ObjectError> errors=ex.getAllErrors();
    ObjectError error=errors.get(0);
    String msg=error.getDefaultMessage();
    logger.error(String.format(msg,msg));
    return ResultGeekQ.error(SESSION_ERROR);
  }
 else {
    return ResultGeekQ.error(SYSTEM_ERROR);
  }
}
