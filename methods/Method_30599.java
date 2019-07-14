private void onCallResponse(final boolean successful,final ResponseType response,final ApiError error){
  postOnResumed(new Runnable(){
    @Override public void run(){
      mRequesting=false;
      onRequestFinished(successful,mRequestState,response,error);
      clearRequest();
    }
  }
);
}
