/** 
 * ?????????
 */
@ExceptionHandler(UndeclaredThrowableException.class) @ResponseStatus(HttpStatus.UNAUTHORIZED) @ResponseBody public ErrorResponseData credentials(UndeclaredThrowableException e){
  getRequest().setAttribute("tip","????");
  log.error("????!",e);
  return new ErrorResponseData(BizExceptionEnum.NO_PERMITION.getCode(),BizExceptionEnum.NO_PERMITION.getMessage());
}
