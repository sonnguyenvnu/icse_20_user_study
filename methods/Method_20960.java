@Override public @NonNull Observable<MessageThreadEnvelope> fetchMessagesForThread(final @NonNull Long messageThreadId){
  return Observable.just(MessageThreadEnvelopeFactory.messageThreadEnvelope());
}
