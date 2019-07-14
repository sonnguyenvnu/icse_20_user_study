@Override protected ApiRequest<ItemCollectionList> onCreateRequest(Integer start,Integer count){
  return ApiService.getInstance().getItemCollectionList(mItemType,mItemId,mFollowingsFirst,start,count);
}
