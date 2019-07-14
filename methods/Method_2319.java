/** 
 * ??????
 * @param request
 * @param response
 * @param exception
 */
@ExceptionHandler public String exceptionHandler(HttpServletRequest request,HttpServletResponse response,Exception exception){
  LOGGER.error("???????",exception);
  request.setAttribute("ex",exception);
  if (null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
    request.setAttribute("requestHeader","ajax");
  }
  if (exception instanceof UnauthorizedException) {
    return "/403.jsp";
  }
  if (exception instanceof InvalidSessionException) {
    return "/error.jsp";
  }
  return "/error.jsp";
}
