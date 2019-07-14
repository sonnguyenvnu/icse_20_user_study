@Override protected void onRequestFinished(boolean successful,Void requestState,ResponseType response,ApiError error){
  onLoadFinished(successful,response,error);
}
