@Override public @NonNull Observable<MessageThreadEnvelope> fetchMessagesForThread(final @NonNull Long messageThreadId){
  return this.service.messagesForThread(messageThreadId).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
