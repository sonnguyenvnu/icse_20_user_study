@Override public final void prepareSource(SourceInfoRefreshListener listener,@Nullable TransferListener mediaTransferListener){
  Looper looper=Looper.myLooper();
  Assertions.checkArgument(this.looper == null || this.looper == looper);
  sourceInfoListeners.add(listener);
  if (this.looper == null) {
    this.looper=looper;
    prepareSourceInternal(mediaTransferListener);
  }
 else   if (timeline != null) {
    listener.onSourceInfoRefreshed(this,timeline,manifest);
  }
}
