/** 
 * @param response
 * @param result
 */
protected void write(HttpServletResponse response,String result){
  try {
    response.setContentType("text/javascript");
    response.getWriter().print(result);
    response.getWriter().flush();
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
}
