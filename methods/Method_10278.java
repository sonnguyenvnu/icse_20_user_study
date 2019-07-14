/** 
 * Perform a HTTP GET request and track the Android Context which initiated the request.
 * @param context         the Android Context which initiated the request.
 * @param url             the URL to send the request to.
 * @param entity          a raw {@link cz.msebera.android.httpclient.HttpEntity} to send with the request, forexample, use this to send string/json/xml payloads to a server by passing a  {@link cz.msebera.android.httpclient.entity.StringEntity}.
 * @param contentType     the content type of the payload you are sending, for exampleapplication/json if sending a json payload.
 * @param responseHandler the response ha   ndler instance that should handle the response.
 * @return RequestHandle of future request process
 */
public RequestHandle get(Context context,String url,HttpEntity entity,String contentType,ResponseHandlerInterface responseHandler){
  return sendRequest(httpClient,httpContext,addEntityToRequestBase(new HttpGet(URI.create(url).normalize()),entity),contentType,responseHandler,context);
}
