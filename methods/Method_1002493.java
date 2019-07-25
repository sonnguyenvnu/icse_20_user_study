/** 
 * @param request   a StreamRequest object to be encoded as a tunneled POST
 * @param requestContext a RequestContext object associated with the request
 * @param threshold the size of the query params above which the request will be encoded
 * @param callback the callback to be executed with the encoded request
 */
public static void encode(final StreamRequest request,RequestContext requestContext,int threshold,final Callback<StreamRequest> callback){
  URI uri=request.getURI();
  String query=uri.getRawQuery();
  boolean forceQueryTunnel=requestContext.getLocalAttr(R2Constants.FORCE_QUERY_TUNNEL) != null && (Boolean)requestContext.getLocalAttr(R2Constants.FORCE_QUERY_TUNNEL);
  if (query == null || query.length() == 0 || (query.length() < threshold && !forceQueryTunnel)) {
    callback.onSuccess(request);
  }
 else {
    Messages.toRestRequest(request,new Callback<RestRequest>(){
      @Override public void onError(      Throwable e){
        callback.onError(e);
      }
      @Override public void onSuccess(      RestRequest result){
        RestRequest encodedRequest;
        try {
          encodedRequest=doEncode(result);
        }
 catch (        Exception ex) {
          callback.onError(ex);
          return;
        }
        callback.onSuccess(Messages.toStreamRequest(encodedRequest));
      }
    }
);
  }
}
