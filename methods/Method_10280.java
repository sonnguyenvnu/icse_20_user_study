/** 
 * Instantiate a new asynchronous HTTP request for the passed parameters.
 * @param client          HttpClient to be used for request, can differ in single requests
 * @param contentType     MIME body type, for POST and PUT requests, may be null
 * @param context         Context of Android application, to hold the reference of request
 * @param httpContext     HttpContext in which the request will be executed
 * @param responseHandler ResponseHandler or its subclass to put the response into
 * @param uriRequest      instance of HttpUriRequest, which means it must be of HttpDelete,HttpPost, HttpGet, HttpPut, etc.
 * @return AsyncHttpRequest ready to be dispatched
 */
protected AsyncHttpRequest newAsyncHttpRequest(DefaultHttpClient client,HttpContext httpContext,HttpUriRequest uriRequest,String contentType,ResponseHandlerInterface responseHandler,Context context){
  return new AsyncHttpRequest(client,httpContext,uriRequest,responseHandler);
}
