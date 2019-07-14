@Override protected ApiRequest<PhotoList> onCreateRequest(Integer start,Integer count){
  return ApiService.getInstance().getItemPhotoList(mItemType,mItemId,start,count);
}
