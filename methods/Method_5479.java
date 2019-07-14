@Override public void prepareSourceInternal(@Nullable TransferListener mediaTransferListener){
  transferListener=mediaTransferListener;
  refreshSourceInfo(timeline,null);
}
