public void cancelApiRequests(){
  mLifeStreamHttpClient.dispatcher().cancelAll();
  mFrodoHttpClient.dispatcher().cancelAll();
}
