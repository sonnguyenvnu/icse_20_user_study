/** 
 * ???
 */
@ExceptionHandler({Exception.class}) @ResponseStatus(HttpStatus.OK) public String processException(Exception e,HttpServletRequest request){
  LOG.error("Exception",e);
  request.setAttribute("msg","????");
  return "common/error";
}
