@Override protected ApiRequest<User> onCreateRequest(){
  return ApiService.getInstance().follow(mUserIdOrUid,mFollow);
}
