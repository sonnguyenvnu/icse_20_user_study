@Override public @NonNull Observable<MessageThread> markAsRead(final @NonNull MessageThread messageThread){
  return this.service.markAsRead(messageThread.id()).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
