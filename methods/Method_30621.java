@Override protected void onLoadFinished(boolean more,int count,boolean successful,List<Photo> response,ApiError error){
  if (successful) {
    if (more) {
      append(response);
      getListener().onLoadPhotoListFinished(getRequestCode());
      getListener().onPhotoListAppended(getRequestCode(),Collections.unmodifiableList(response));
    }
 else {
      set(response);
      getListener().onLoadPhotoListFinished(getRequestCode());
      getListener().onPhotoListChanged(getRequestCode(),Collections.unmodifiableList(get()));
    }
  }
 else {
    getListener().onLoadPhotoListFinished(getRequestCode());
    getListener().onLoadPhotoListError(getRequestCode(),error);
  }
}
