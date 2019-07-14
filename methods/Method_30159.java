@Override protected ApiRequest<ItemType> onCreateRequest(){
  return ApiService.getInstance().getItem(getItemType(),mItemId);
}
