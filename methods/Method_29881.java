private void onErrorSimple(ApiError error){
  LogUtils.e(error.toString());
  Context context=getContext();
  ToastUtils.show(context.getString(R.string.broadcast_send_failed_format,ApiError.getErrorString(error,context)),context);
  EventBusUtils.postAsync(new BroadcastSendErrorEvent(mId,this));
  stopSelf();
}
