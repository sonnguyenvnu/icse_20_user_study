/** 
 * Get target service name from request
 * @param request Request object
 * @return service name
 */
protected String getTargetServiceName(Object request){
  if (request instanceof RequestBase) {
    RequestBase requestBase=(RequestBase)request;
    return requestBase.getTargetServiceUniqueName();
  }
  return null;
}
