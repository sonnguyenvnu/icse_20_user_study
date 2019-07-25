@Override public void close(){
  if (!initialEndpointsFuture.isDone()) {
    initialEndpointsFuture.cancel(true);
  }
}
