@Override public boolean shouldRetrySend(@NonNull List<ReportSender> senders,@NonNull List<FailedSender> failedSenders){
  return (senders.size() == failedSenders.size()) && !senders.isEmpty();
}
