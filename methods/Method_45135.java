public boolean shouldFailover(final org.apache.http.HttpResponse remoteResponse){
  int statusCode=remoteResponse.getStatusLine().getStatusCode();
  return statuses.contains(statusCode);
}
