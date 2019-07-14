@Override protected void onLoadFinished(boolean successful,Rating response,ApiError error){
  if (successful) {
    set(response);
    getListener().onLoadRatingFinished(getRequestCode());
    getListener().onRatingChanged(getRequestCode(),response);
  }
 else {
    getListener().onLoadRatingFinished(getRequestCode());
    getListener().onLoadRatingError(getRequestCode(),error);
  }
}
