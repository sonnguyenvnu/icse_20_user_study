@Override public @NonNull Observable<CommentsEnvelope> fetchComments(final @NonNull Update update){
  return Observable.just(CommentsEnvelopeFactory.commentsEnvelope());
}
