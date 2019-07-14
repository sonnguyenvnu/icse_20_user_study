public void onUserException(Throwable error){
  AsyncHttpClient.log.e(LOG_TAG,"User-space exception detected!",error);
  throw new RuntimeException(error);
}
