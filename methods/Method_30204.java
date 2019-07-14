@Override protected void onLoadFinished(boolean more,int count,boolean successful,List<SimpleItemForumTopic> response,ApiError error){
  if (successful) {
    if (more) {
      append(response);
      getListener().onLoadForumTopicListFinished(getRequestCode());
      getListener().onForumTopicListAppended(getRequestCode(),Collections.unmodifiableList(response));
    }
 else {
      set(response);
      getListener().onLoadForumTopicListFinished(getRequestCode());
      getListener().onForumTopicListChanged(getRequestCode(),Collections.unmodifiableList(get()));
    }
  }
 else {
    getListener().onLoadForumTopicListFinished(getRequestCode());
    getListener().onLoadForumTopicListError(getRequestCode(),error);
  }
}
