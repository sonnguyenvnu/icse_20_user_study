@Override protected void onLoadFinished(boolean successful,List<CollectableItem> response,ApiError error){
  if (successful) {
    set(response);
    getListener().onLoadRecommendationListFinished(getRequestCode());
    getListener().onRecommendationListChanged(getRequestCode(),response);
  }
 else {
    getListener().onLoadRecommendationListFinished(getRequestCode());
    getListener().onLoadRecommendationListError(getRequestCode(),error);
  }
}
