/** 
 * ??????
 */
@ExceptionHandler(ServiceException.class) @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) @ResponseBody public ErrorResponseData bussiness(ServiceException e){
  LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(),e));
  getRequest().setAttribute("tip",e.getMessage());
  log.error("????:",e);
  return new ErrorResponseData(e.getCode(),e.getMessage());
}
