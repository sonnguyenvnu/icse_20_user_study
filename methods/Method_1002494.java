/** 
 * Takes a Request object that has been encoded for tunnelling as a POST with an X-HTTP-Override-Method header and creates a new request that represents the intended original request
 * @param request the request to be decoded
 * @param requestContext a RequestContext object associated with the request
 * @param callback the callback to be executed with the decoded request
 */
public static void decode(final StreamRequest request,final RequestContext requestContext,final Callback<StreamRequest> callback){
  if (request.getHeader(HEADER_METHOD_OVERRIDE) == null) {
    callback.onSuccess(request);
  }
 else {
    Messages.toRestRequest(request,new Callback<RestRequest>(){
      @Override public void onError(      Throwable e){
        callback.onError(e);
      }
      @Override public void onSuccess(      RestRequest result){
        RestRequest decodedRequest;
        try {
          decodedRequest=doDecode(result,requestContext);
        }
 catch (        Exception ex) {
          callback.onError(ex);
          return;
        }
        callback.onSuccess(Messages.toStreamRequest(decodedRequest));
      }
    }
);
  }
}
