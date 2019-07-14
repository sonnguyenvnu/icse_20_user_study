@Override protected ApiRequest<List<CollectableItem>> onCreateRequest(){
  return ApiService.getInstance().getItemRecommendationList(mItemType,mItemId,null);
}
