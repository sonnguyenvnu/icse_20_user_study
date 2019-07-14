/** 
 * 3. ???????????
 * @param request   ????
 * @param response  ????
 * @param throwable ??
 */
public static void serverSend(SofaRequest request,SofaResponse response,Throwable throwable){
  if (openTrace) {
    try {
      tracer.serverSend(request,response,throwable);
    }
 catch (    Exception e) {
      if (LOGGER.isWarnEnabled()) {
        LOGGER.warn(e.getMessage(),e);
      }
    }
  }
}
