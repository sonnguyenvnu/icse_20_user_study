@Override protected ApiRequest<Comment> onCreateRequest(){
  return ApiService.getInstance().sendBroadcastComment(mBroadcastId,mComment);
}
