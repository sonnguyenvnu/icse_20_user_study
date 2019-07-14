@Override public @NonNull Observable<MessageThreadsEnvelope> fetchMessageThreadsWithPaginationPath(final @NonNull String paginationPath){
  return this.service.paginatedMessageThreads(paginationPath).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
