@Override protected ApiRequest<User> onCreateRequest(){
  return ApiService.getInstance().getUser(mUserIdOrUid);
}
