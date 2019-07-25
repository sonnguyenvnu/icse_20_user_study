/** 
 * Handles this context with handlers.
 */
public void handle(){
  try {
    for (handleIndex++; handleIndex < handlers.size(); handleIndex++) {
      handlers.get(handleIndex).handle(this);
    }
  }
 catch (  final Exception e) {
    final String requestLog=Requests.getLog(request);
    LOGGER.log(Level.ERROR,"Handler process failed: " + requestLog,e);
    setRenderer(new Http500Renderer(e));
  }
}
