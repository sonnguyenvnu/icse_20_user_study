@Override public void prepareSourceInternal(@Nullable TransferListener mediaTransferListener){
  transferListener=mediaTransferListener;
  notifySourceInfoRefreshed(timelineDurationUs,timelineIsSeekable);
}
