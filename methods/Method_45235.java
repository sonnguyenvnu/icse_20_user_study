@Override public void onMessageLeave(final Response response){
  for (  MocoMonitor monitor : monitors) {
    monitor.onMessageLeave(response);
  }
}
