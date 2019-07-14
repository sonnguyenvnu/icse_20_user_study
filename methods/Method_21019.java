@Override public @NonNull Observable<CommentsEnvelope> fetchComments(final @NonNull String paginationPath){
  return this.service.paginatedProjectComments(paginationPath).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
