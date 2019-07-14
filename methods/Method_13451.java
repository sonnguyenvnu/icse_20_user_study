@Override public final void doUnsubscribe(URL url,NotifyListener listener){
  if (isAdminURL(url)) {
    shutdownServiceNamesLookup();
  }
}
