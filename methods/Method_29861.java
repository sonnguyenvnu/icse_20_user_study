@Override public void onErrorResponse(ApiError error){
  LogUtils.e(error.toString());
  Context context=getContext();
  ToastUtils.show(context.getString(R.string.broadcast_rebroadcast_failed_format,ApiError.getErrorString(error,context)),context);
  EventBusUtils.postAsync(new BroadcastWriteFinishedEvent(mBroadcastId,this));
  EventBusUtils.postAsync(new BroadcastRebroadcastErrorEvent(mBroadcastId,this));
  stopSelf();
}
