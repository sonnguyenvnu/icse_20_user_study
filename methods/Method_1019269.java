/** 
 * Dispatches the given request to the appropriate handler. 
 */
public void dispatch(WorkerRequest request) throws Exception {
  Class<? extends WorkerRequest> requestType=request.type();
  Provider<RequestHandler> handlerProvider=checkNotNull(requestHandlers.get(requestType),"No handler found for request of type %s",requestType.getName());
  handlerProvider.get().handleRequest(request);
}
