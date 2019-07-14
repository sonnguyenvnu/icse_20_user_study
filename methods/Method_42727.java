/** 
 * ???
 */
@ExceptionHandler({Exception.class}) @ResponseStatus(HttpStatus.OK) public String processException(Exception e,HttpServletRequest request){
  LOG.error("Exception",e);
  DwzAjax dwz=new DwzAjax();
  dwz.setStatusCode(DWZ.ERROR);
  dwz.setMessage("????");
  request.setAttribute("dwz",dwz);
  return "common/ajaxDone";
}
