@Override public void onErrorResponse(ApiError error){
  LogUtils.e(error.toString());
  Context context=getContext();
  ToastUtils.show(context.getString(R.string.broadcast_send_comment_failed_format,ApiError.getErrorString(error,context)),context);
  EventBusUtils.postAsync(new BroadcastCommentSendErrorEvent(mBroadcastId,this));
  stopSelf();
}
