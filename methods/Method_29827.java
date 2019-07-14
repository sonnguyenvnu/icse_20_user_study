private void onLoadFinished(boolean more,int count,boolean successful,List<Comment> response,ApiError error){
  if (successful) {
    if (more) {
      append(response);
      getListener().onLoadCommentListFinished(getRequestCode());
      getListener().onCommentListAppended(getRequestCode(),Collections.unmodifiableList(response));
    }
 else {
      set(response);
      getListener().onLoadCommentListFinished(getRequestCode());
      getListener().onCommentListChanged(getRequestCode(),Collections.unmodifiableList(get()));
    }
  }
 else {
    getListener().onLoadCommentListFinished(getRequestCode());
    getListener().onLoadCommentListError(getRequestCode(),error);
  }
}
