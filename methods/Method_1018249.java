/** 
 * request initialize work
 */
private void init(){
  response.setCharacterEncoding(config.getCharset());
  response.setContentType("text/html;charset=" + config.getCharset());
  response.setStatus(HttpServletResponse.SC_OK);
}
