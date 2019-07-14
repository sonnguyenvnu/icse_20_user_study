/** 
 * ??json??
 * @param response
 * @param message
 */
public void sendMessage(HttpServletResponse response,String message){
  response.reset();
  response.setContentType("application/X-JSON;charset=UTF-8");
  PrintWriter printWriter=null;
  try {
    printWriter=response.getWriter();
    printWriter.write(message);
  }
 catch (  IOException e) {
    logger.error(ExceptionUtils.getFullStackTrace(e));
  }
 finally {
    if (printWriter != null) {
      printWriter.flush();
      printWriter.close();
    }
  }
}
