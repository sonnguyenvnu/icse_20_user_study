@Override public @NonNull Observable<CommentsEnvelope> fetchComments(final @NonNull Update update){
  return this.service.updateComments(update.projectId(),update.id()).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
