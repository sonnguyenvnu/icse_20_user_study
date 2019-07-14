@Override protected void onLoadFinished(boolean more,int count,boolean successful,List<SimpleReview> response,ApiError error){
  if (successful) {
    if (more) {
      append(response);
      getListener().onLoadReviewListFinished(getRequestCode());
      getListener().onReviewListAppended(getRequestCode(),Collections.unmodifiableList(response));
    }
 else {
      set(response);
      getListener().onLoadReviewListFinished(getRequestCode());
      getListener().onReviewListChanged(getRequestCode(),Collections.unmodifiableList(get()));
    }
    for (    SimpleReview review : response) {
      EventBusUtils.postAsync(new ReviewUpdatedEvent(review,this));
    }
  }
 else {
    getListener().onLoadReviewListFinished(getRequestCode());
    getListener().onLoadReviewListError(getRequestCode(),error);
  }
}
