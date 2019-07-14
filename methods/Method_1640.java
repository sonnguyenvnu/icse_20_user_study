@VisibleForTesting void fetchSync(HttpUrlConnectionNetworkFetchState fetchState,Callback callback){
  HttpURLConnection connection=null;
  InputStream is=null;
  try {
    connection=downloadFrom(fetchState.getUri(),MAX_REDIRECTS);
    fetchState.responseTime=mMonotonicClock.now();
    if (connection != null) {
      is=connection.getInputStream();
      callback.onResponse(is,-1);
    }
  }
 catch (  IOException e) {
    callback.onFailure(e);
  }
 finally {
    if (is != null) {
      try {
        is.close();
      }
 catch (      IOException e) {
      }
    }
    if (connection != null) {
      connection.disconnect();
    }
  }
}
