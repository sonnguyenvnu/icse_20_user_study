@Override protected void onLoadFinished(boolean successful,ItemType response,ApiError error){
  if (successful) {
    set(response);
    getListener().onLoadItemFinished(getRequestCode());
    getListener().onItemChanged(getRequestCode(),get());
  }
 else {
    getListener().onLoadItemFinished(getRequestCode());
    getListener().onLoadItemError(getRequestCode(),error);
  }
}
