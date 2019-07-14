/** 
 * Cancels all pending (or potentially active) requests. <p>&nbsp;</p> <b>Note:</b> This will only affect requests which were created with a non-null android Context. This method is intended to be used in the onDestroy method of your android activities to destroy all requests which are no longer required.
 * @param mayInterruptIfRunning specifies if active requests should be cancelled along withpending requests.
 */
public void cancelAllRequests(boolean mayInterruptIfRunning){
  for (  List<RequestHandle> requestList : requestMap.values()) {
    if (requestList != null) {
      for (      RequestHandle requestHandle : requestList) {
        requestHandle.cancel(mayInterruptIfRunning);
      }
    }
  }
  requestMap.clear();
}
