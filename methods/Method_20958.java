@Override public @NonNull Observable<MessageThreadEnvelope> fetchMessagesForBacking(final @NonNull Backing backing){
  return Observable.just(MessageThreadEnvelopeFactory.messageThreadEnvelope());
}
