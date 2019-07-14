private void onLoadFinished(boolean successful,List<SimpleCelebrity> response,ApiError error){
  if (successful) {
    set(response);
    getListener().onLoadCelebrityListFinished(getRequestCode());
    getListener().onCelebrityListChanged(getRequestCode(),response);
  }
 else {
    getListener().onLoadCelebrityListFinished(getRequestCode());
    getListener().onLoadCelebrityListError(getRequestCode(),error);
  }
}
