private void onErrorWithImages(ApiError error){
  LogUtils.e(error.toString());
  Context context=getContext();
  ToastUtils.showLong(context.getString(R.string.broadcast_send_failed_format,ApiError.getErrorString(error,context)),context);
  notifyError(error);
  EventBusUtils.postAsync(new BroadcastSendErrorEvent(mId,this));
  stopSelf();
}
