@Override public void run(){
  setStatus(Status.RUNNING);
  ServerConnection conn=factory.get(url);
  if (isInterrupted() || conn == null) {
    setStatus(Status.FAILED);
    callback.onFailure();
    return;
  }
  conn.performDnsRequest(QUERY,QUERY_DATA,new QueryCallback());
}
