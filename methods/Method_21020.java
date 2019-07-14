@Override public @NonNull Observable<MessageThreadEnvelope> fetchMessagesForBacking(final @NonNull Backing backing){
  return this.service.messagesForBacking(backing.projectId(),backing.backerId()).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
