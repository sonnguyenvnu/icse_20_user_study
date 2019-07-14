protected void setAndNotifyListener(List<Broadcast> broadcastList,boolean notifyFinished){
  set(broadcastList);
  if (notifyFinished) {
    getListener().onLoadBroadcastListFinished(getRequestCode());
  }
  getListener().onBroadcastListChanged(getRequestCode(),Collections.unmodifiableList(get()));
}
