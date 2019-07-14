@Override protected void onLoadFinished(boolean more,int count,boolean successful,List<Broadcast> response,ApiError error){
  if (successful) {
    if (more) {
      append(response);
      getListener().onLoadBroadcastListFinished(getRequestCode());
      getListener().onBroadcastListAppended(getRequestCode(),Collections.unmodifiableList(response));
    }
 else {
      setAndNotifyListener(response,true);
    }
  }
 else {
    getListener().onLoadBroadcastListFinished(getRequestCode());
    getListener().onLoadBroadcastListError(getRequestCode(),error);
  }
}
