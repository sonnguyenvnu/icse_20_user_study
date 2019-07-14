@Override protected ApiRequest<Void> onCreateRequest(){
  return ApiService.getInstance().deleteBroadcast(mBroadcastId);
}
