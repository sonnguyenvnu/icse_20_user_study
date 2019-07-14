@Override public @NonNull Observable<MessageThreadEnvelope> fetchMessagesForThread(final @NonNull MessageThread messageThread){
  return this.service.messagesForThread(messageThread.id()).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
