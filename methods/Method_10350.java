@Override public void onCancelButtonPressed(){
  getAsyncHttpClient().cancelAllRequests(true);
}
