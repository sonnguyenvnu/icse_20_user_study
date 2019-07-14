@Override protected ApiRequest<CelebrityList> onCreateRequest(){
  return ApiService.getInstance().getItemCelebrityList(mItemType,mItemId);
}
