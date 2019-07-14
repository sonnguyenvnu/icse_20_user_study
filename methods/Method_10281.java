/** 
 * Puts a new request in queue as a new thread in pool to be executed
 * @param client          HttpClient to be used for request, can differ in single requests
 * @param contentType     MIME body type, for POST and PUT requests, may be null
 * @param context         Context of Android application, to hold the reference of request
 * @param httpContext     HttpContext in which the request will be executed
 * @param responseHandler ResponseHandler or its subclass to put the response into
 * @param uriRequest      instance of HttpUriRequest, which means it must be of HttpDelete,HttpPost, HttpGet, HttpPut, etc.
 * @return RequestHandle of future request process
 */
protected RequestHandle sendRequest(DefaultHttpClient client,HttpContext httpContext,HttpUriRequest uriRequest,String contentType,ResponseHandlerInterface responseHandler,Context context){
  if (uriRequest == null) {
    throw new IllegalArgumentException("HttpUriRequest must not be null");
  }
  if (responseHandler == null) {
    throw new IllegalArgumentException("ResponseHandler must not be null");
  }
  if (responseHandler.getUseSynchronousMode() && !responseHandler.getUsePoolThread()) {
    throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
  }
  if (contentType != null) {
    if (uriRequest instanceof HttpEntityEnclosingRequestBase && ((HttpEntityEnclosingRequestBase)uriRequest).getEntity() != null && uriRequest.containsHeader(HEADER_CONTENT_TYPE)) {
      log.w(LOG_TAG,"Passed contentType will be ignored because HttpEntity sets content type");
    }
 else {
      uriRequest.setHeader(HEADER_CONTENT_TYPE,contentType);
    }
  }
  responseHandler.setRequestHeaders(uriRequest.getAllHeaders());
  responseHandler.setRequestURI(uriRequest.getURI());
  AsyncHttpRequest request=newAsyncHttpRequest(client,httpContext,uriRequest,contentType,responseHandler,context);
  threadPool.submit(request);
  RequestHandle requestHandle=new RequestHandle(request);
  if (context != null) {
    List<RequestHandle> requestList;
synchronized (requestMap) {
      requestList=requestMap.get(context);
      if (requestList == null) {
        requestList=Collections.synchronizedList(new LinkedList<RequestHandle>());
        requestMap.put(context,requestList);
      }
    }
    requestList.add(requestHandle);
    Iterator<RequestHandle> iterator=requestList.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().shouldBeGarbageCollected()) {
        iterator.remove();
      }
    }
  }
  return requestHandle;
}
