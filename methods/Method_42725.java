/** 
 * shiro?? ?? <p/> ?????????????
 */
@ExceptionHandler({UnauthorizedException.class}) @ResponseStatus(HttpStatus.OK) public String processUnauthorizedException(HttpServletRequest request,UnauthorizedException e){
  LOG.error("UnauthorizedException",e);
  DwzAjax dwz=new DwzAjax();
  dwz.setStatusCode(DWZ.ERROR);
  dwz.setMessage("???????");
  request.setAttribute("dwz",dwz);
  return "common/ajaxDone";
}
