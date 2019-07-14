/** 
 * 2. ????????
 * @param request ????
 */
public static void serverReceived(SofaRequest request){
  if (openTrace) {
    try {
      tracer.serverReceived(request);
    }
 catch (    Exception e) {
      if (LOGGER.isWarnEnabled()) {
        LOGGER.warn(e.getMessage(),e);
      }
    }
  }
}
