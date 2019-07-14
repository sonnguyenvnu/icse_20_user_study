@Override protected ApiRequest<ItemCollection> onCreateRequest(){
  return ApiService.getInstance().voteItemCollection(mItemType,mItemId,mItemCollectionId);
}
