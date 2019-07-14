@Override protected void onLoadFinished(boolean successful,Broadcast response,ApiError error){
  if (successful) {
    set(response);
    getListener().onLoadBroadcastFinished(getRequestCode());
    getListener().onBroadcastChanged(getRequestCode(),response);
    EventBusUtils.postAsync(new BroadcastUpdatedEvent(response,this));
  }
 else {
    getListener().onLoadBroadcastFinished(getRequestCode());
    getListener().onLoadBroadcastError(getRequestCode(),error);
  }
}
