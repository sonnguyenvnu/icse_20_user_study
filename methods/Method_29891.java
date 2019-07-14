private void onLoadFinished(boolean more,int count,boolean successful,List<Broadcast> response,ApiError error){
  if (successful) {
    if (more) {
      append(response);
      getListener().onLoadBroadcastListFinished(getRequestCode());
      getListener().onBroadcastListAppended(getRequestCode(),Collections.unmodifiableList(response));
    }
 else {
      setAndNotifyListener(response,true);
    }
    for (    Broadcast broadcast : response) {
      EventBusUtils.postAsync(new BroadcastUpdatedEvent(broadcast,this));
    }
    setCanLoadMore(count == 0 || response.size() > 0);
  }
 else {
    getListener().onLoadBroadcastListFinished(getRequestCode());
    getListener().onLoadBroadcastListError(getRequestCode(),error);
  }
}
