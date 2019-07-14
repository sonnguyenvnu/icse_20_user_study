@Override protected ApiRequest<Rating> onCreateRequest(){
  return ApiService.getInstance().getItemRating(mItemType,mItemId);
}
