@Override public @NonNull Observable<MessageThreadEnvelope> fetchMessagesForThread(final @NonNull MessageThread messageThread){
  return Observable.just(MessageThreadEnvelopeFactory.messageThreadEnvelope());
}
