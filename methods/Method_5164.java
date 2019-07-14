@Override @CallSuper public void prepareSourceInternal(@Nullable TransferListener mediaTransferListener){
  this.mediaTransferListener=mediaTransferListener;
  eventHandler=new Handler();
}
