/** 
 * ???? <p/> ?????????????
 */
@ExceptionHandler({BizException.class}) @ResponseStatus(HttpStatus.OK) public String processBizException(HttpServletRequest request,BizException e){
  LOG.error("BizException",e);
  DwzAjax dwz=new DwzAjax();
  dwz.setStatusCode(DWZ.ERROR);
  dwz.setMessage(e.getMsg());
  request.setAttribute("dwz",dwz);
  return "common/ajaxDone";
}
