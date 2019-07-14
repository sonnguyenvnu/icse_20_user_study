@Override protected void onLoadFinished(boolean successful,User response,ApiError error){
  if (successful) {
    set(response);
    onLoadSuccess(response);
    getListener().onLoadUserFinished(getRequestCode());
    getListener().onUserChanged(getRequestCode(),get());
    EventBusUtils.postAsync(new UserUpdatedEvent(response,this));
  }
 else {
    getListener().onLoadUserFinished(getRequestCode());
    getListener().onLoadUserError(getRequestCode(),error);
  }
}
