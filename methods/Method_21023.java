@Override public @NonNull Observable<MessageThreadsEnvelope> fetchMessageThreads(final @Nullable Project project,final @NonNull Mailbox mailbox){
  final Observable<Response<MessageThreadsEnvelope>> apiResponse=project == null ? this.service.messageThreads(mailbox.getType()) : this.service.messageThreads(project.id(),mailbox.getType());
  return apiResponse.lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
