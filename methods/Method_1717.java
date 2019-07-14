/** 
 * Handles exceptions. <p> OkHttp notifies callers of cancellations via an IOException. If IOException is caught after request cancellation, then the exception is interpreted as successful cancellation and onCancellation is called. Otherwise onFailure is called.
 */
private void handleException(final Call call,final Exception e,final Callback callback){
  if (call.isCanceled()) {
    callback.onCancellation();
  }
 else {
    callback.onFailure(e);
  }
}
