private void cancelRequests(final List<RequestHandle> requestList,final boolean mayInterruptIfRunning){
  if (requestList != null) {
    for (    RequestHandle requestHandle : requestList) {
      requestHandle.cancel(mayInterruptIfRunning);
    }
  }
}
