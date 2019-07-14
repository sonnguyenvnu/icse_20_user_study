@Override protected ApiRequest<UserItemList> onCreateRequest(){
  return ApiService.getInstance().getUserItemList(mUserIdOrUid);
}
