/** 
 * Proxies requests to the underlying container given the incoming Lambda request. This method returns a populated return object for the Lambda function.
 * @param request The incoming Lambda request
 * @param context The execution context for the Lambda function
 * @return A valid response type
 */
public ResponseType proxy(RequestType request,Context context){
  lambdaContext=context;
  try {
    SecurityContext securityContext=securityContextWriter.writeSecurityContext(request,context);
    CountDownLatch latch=new CountDownLatch(1);
    ContainerRequestType containerRequest=requestReader.readRequest(request,securityContext,context,config);
    ContainerResponseType containerResponse=getContainerResponse(containerRequest,latch);
    handleRequest(containerRequest,containerResponse,context);
    latch.await();
    if (logFormatter != null) {
      log.info(SecurityUtils.crlf(logFormatter.format(containerRequest,containerResponse,securityContext)));
    }
    return responseWriter.writeResponse(containerResponse,context);
  }
 catch (  Exception e) {
    log.error("Error while handling request",e);
    return exceptionHandler.handle(e);
  }
}
