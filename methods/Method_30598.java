protected void start(RequestStateType requestState){
  if (mRequesting || shouldIgnoreStartRequest()) {
    return;
  }
  mRequesting=true;
  mRequest=onCreateRequest(requestState);
  mRequestState=requestState;
  mRequest.enqueue(this);
  onRequestStarted();
}
