/** 
 * ???? <p/> ?????????????
 */
@ExceptionHandler({BizException.class}) @ResponseStatus(HttpStatus.OK) public String processBizException(HttpServletRequest request,BizException e){
  LOG.error("BizException",e);
  request.setAttribute("msg",e.getMsg());
  return "common/error";
}
