/** 
 * 4. ???????????
 * @param request   ????
 * @param response  ????
 * @param throwable ??
 */
public static void clientReceived(SofaRequest request,SofaResponse response,Throwable throwable){
  if (openTrace) {
    try {
      tracer.clientReceived(request,response,throwable);
    }
 catch (    Exception e) {
      if (LOGGER.isWarnEnabled()) {
        LOGGER.warn(e.getMessage(),e);
      }
    }
  }
}
